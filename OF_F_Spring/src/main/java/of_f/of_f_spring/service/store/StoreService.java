package of_f.of_f_spring.service.store;

import lombok.extern.slf4j.Slf4j;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.menu.*;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.*;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.domain.mapper.user.UserMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.*;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.repository.store.*;
import of_f.of_f_spring.repository.user.UserRepository;
import of_f.of_f_spring.service.config.ImgService;
import of_f.of_f_spring.service.user.EmailService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreCategoryRepository storeCategoryRepository;

    @Autowired
    private StoreSideCategoryRepository storeSideCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @Autowired
    private QRStoreInfoRepository qrStoreInfoRepository;

    @Autowired
    private StoreSideMenuRepository storeSideMenuRepository;

    @Autowired
    private StoreMSRepository storeMSRepository;

    @Autowired
    private StoreOrderRepository storeOrderRepository;

    public ApiResponseDTO applicationNewStore(StoreDTO storeDTO, Principal principal) {  // 가맹점 신청

        if (principal == null || principal.getName() == null)
            throw new StoreException(StoreExceptionEnum.DOES_NO_EXIT_USER); //존재하지 않는 회원

        User user = userRepository.findByEmail(principal.getName());

        if (user == null)
            throw new StoreException(StoreExceptionEnum.DOES_NO_EXIT_USER); //존재하지 않는 회원

        if (user.getStores().size() != 0) {
            if (user.getStores().get(0).getStatus() == 5) {
                throw new StoreException(StoreExceptionEnum.CHECK_STORE_STATUS); //이미 존재하는 가게
            }
            throw new StoreException(StoreExceptionEnum.ALREADY_EXIST_STORE); //이미 존재하는 가게
        }

        try {

            storeDTO.setStatus(5);
            storeDTO.setUserSeq(user.getSeq());

            Store store = StoreMapper.instance.storeDTOTOStore(storeDTO);

            storeDTO = StoreMapper.instance.storeToStoreDTO(storeRepository.save(store));

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            log.error(String.valueOf(e));
            throw new StoreException(StoreExceptionEnum.SAME_BUSINESS_NUMBER);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new StoreException(StoreExceptionEnum.REQ_NEW_STORE_ERROR);
        }

        return ApiResponseDTO.builder()
                .message("가맹점 신청 성공")
                .detail("가맹점 신청이 완료되었습니다. 7일 이내 번호 연락 예정")
                .data(storeDTO)
                .build();
    }


    @Transactional(rollbackFor = AdminException.class) //가맹점 신청 응답
    public ApiResponseDTO responseApplicationStore(Long storeId, int status) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null)
            throw new AdminException(AdminExceptionEnum.NO_MATCH_STORE);

        store.setStatus(status);

        if (storeRepository.save(store) != null)
            return emailService.StoreStatusResEmail(status, store.getUser());
        else
            throw new AdminException(AdminExceptionEnum.STORE_STATUS_SAVE_EXCEPTION);

    }

    public ApiResponseDTO getStoreInfoAdmin(Principal principal) { //가맹점 정보

        User user = userRepository.findByEmail(principal.getName());

        checkStoreSize(user.getStores());

        UserDTO userDTO = UserMapper.instance.userTOUserDTO_N_ROLE(user);

        if (userDTO.getStores() != null && userDTO.getStores().size() != 1)
            throw new StoreException(StoreExceptionEnum.ALREADY_EXIST_STORE); // 여러개의 가게를 가지고 있을 경우 -> 추후 오픈 예정

        for (int i = 0; i < userDTO.getStores().size(); i++) { //여러개의 가맹점 중, 활성화가 되어있지 않은 가맹점이면 제외하고 정보를 리턴
            if (userDTO.getStores().get(i).getStatus() != 0) {
                userDTO.getStores().remove(i);
            }
            userDTO.getStores().get(i).setStoreOrders(null);
        }

        if (userDTO.getStores().size() == 0) //모든 가맹점이 비활성화 여서 하나도 없을 시.
            throw new StoreException(StoreExceptionEnum.WAIT_STORE_RESPONSE);

        return ApiResponseDTO.builder()
                .message("가맹점 정보")
                .detail("가맹점의 정보 입니다.")
                .data(userDTO)
                .build();
    }

    public ApiResponseDTO getStoreOrderData(Long storeSeq) {

        List<StoreOrder> storeOrderList = storeOrderRepository.findAllByStoreSeqAndStatusOrStatusAndPayStatusOrderByDateDesc(storeSeq, 0, 1, 1);
        List<StoreOrderDTO> storeOrderDTOList = StoreMapper.instance.storeOrderListToStoreOrderDTOList(storeOrderList);

        return ApiResponseDTO.builder()
                .message("가맹점 주문 정보")
                .detail("가맹점의 주문 정보 입니다.")
                .data(storeOrderDTOList)
                .build();
    }

    public ApiResponseDTO saveCategory(StoreCategoryDTO storeCategoryDTO, Principal principal) { //카테고리 저장
        User user = userRepository.findByEmail(principal.getName());

        checkStoreSize(user.getStores()); //가게가 존재하는지 먼저 체크

        for (int i = 0; i < user.getStores().size(); i++) {
            if (user.getStores().get(i).getSeq() == storeCategoryDTO.getStoreSeq()) {

                user.getStores().get(i).checkStoreStatus(user.getStores().get(i).getStatus()); //가맹점 상태가 어떤지 확인

                try {
                    StoreCategory storeCategory = StoreMapper.instance.storeCategoryDTOToStoreCategory(storeCategoryDTO);
                    return ApiResponseDTO.builder()
                            .message("카테고리 저장 성공")
                            .detail("카테고리를 저장하였습니다.")
                            .data(storeCategoryRepository.save(storeCategory))
                            .build();
                } catch (Exception e) {
                    throw new StoreException(StoreExceptionEnum.FAIL_SAVE_CATEGORY);
                }
            }
        }
        throw new StoreException(StoreExceptionEnum.FAIL_SAVE_CATEGORY);
    }

    public ApiResponseDTO updateCategory(StoreCategoryDTO storeCategoryDTO, Principal principal) { //카테고리 업데이트
        User user = userRepository.findByEmail(principal.getName());

        checkStoreSize(user.getStores()); //가게가 존재하지 않을 경우

        List<StoreCategory> storeCategoryList = findStoreCategory(user, storeCategoryDTO);

        checkCategorySize(storeCategoryList); //가져온 가맹점의 카테고리가 존재하는지 확인하는 부분

        try {

            for (int i = 0; i < storeCategoryList.size(); i++) {
                System.out.println(storeCategoryList.get(i).getSeq() + " || " + storeCategoryDTO.getSeq());
                System.out.println(storeCategoryList.get(i).getSeq() == storeCategoryDTO.getSeq());
                if (storeCategoryList.get(i).getSeq().equals(storeCategoryDTO.getSeq())) {  // 변경해야될 카테고리 부분을 찾았다면?
                    storeCategoryList.get(i).setName(storeCategoryDTO.getName());
                    storeCategoryList.get(i).setStatus(storeCategoryDTO.isStatus());
                    return ApiResponseDTO.builder()
                            .message("카테고리 수정")
                            .detail("카테고리를 수정했습니다.")
                            .data(StoreMapper.instance.storeCategoryToStoreCategoryDTO(storeCategoryRepository.save(storeCategoryList.get(i))))
                            .build();

                }
            }
            throw new StoreException(StoreExceptionEnum.FAIL_UPDATE_CATEGORY);
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.FAIL_UPDATE_CATEGORY);
        }

    }

    public ApiResponseDTO deleteCategory(StoreCategoryDTO storeCategoryDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        checkStoreSize(user.getStores()); //가게가 존재하지 않을 경우

        List<StoreCategory> storeCategoryList = findStoreCategory(user, storeCategoryDTO);

        checkCategorySize(storeCategoryList); //가져온 가맹점의 카테고리가 존재하는지 확인하는 부분

        try {
            for (int i = 0; i < storeCategoryList.size(); i++) {
                if (storeCategoryList.get(i).getSeq().equals(storeCategoryDTO.getSeq())) { //삭제할 카테고리를 찾으면?
                    storeCategoryRepository.delete(storeCategoryList.get(i)); //카테고리 삭제
                    return ApiResponseDTO.builder()
                            .message("카테고리 삭제")
                            .detail("카테고리를 삭제하였습니다.")
                            .data(storeCategoryDTO)
                            .build();
                }
            }
            throw new StoreException(StoreExceptionEnum.DOES_NOT_EXIST_CATEGORY);
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_DELETE_CATEGORY);
        }
    }

    @Transactional
    public ApiResponseDTO saveMenu(StoreMenuDTO storeMenuDTO, List<MultipartFile> imgFile, Principal principal) {

        StoreCategory storeCategory = storeCategoryRepository.findById(storeMenuDTO.getStoreCategorySeq()).orElse(null);

        try {
            checkMenu(storeMenuDTO, principal, storeCategory.getStore()); // request 상태 체크
        } catch (NullPointerException e) {
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_INFO); //존재하지 않는 정보
        }

        List<StoreMenuImg> storeMenuImgs = new ArrayList<>();

        if (imgFile != null) // 이미지가 존재하는 경우
            storeMenuImgs = imgService.saveMenuImg(imgFile, storeCategory.getStore());

        StoreMenu storeMenu = StoreMapper.instance.storeMenuToStoreMenuDTO(storeMenuDTO);

        storeMenu.setStoreMenuImgs(storeMenuImgs);

        try {
            storeMenu = storeMenuRepository.save(storeMenu);
        } catch (Exception e) {
            if (imgFile != null)
                imgService.deleteMenuImg(storeMenuImgs);
            throw new StoreException(StoreExceptionEnum.CAN_NOT_SAVE_MENU);
        }


        return ApiResponseDTO.builder()
                .message("메뉴 저장")
                .detail(storeMenu.getName() + " 메뉴를 저장했습니다.")
                .data(storeMenu)
                .build();
    }

    @Transactional
    public ApiResponseDTO updateMenu(StoreMenuDTO storeMenuDTO, List<MultipartFile> imgFile, Principal principal) {

        StoreMenu storeMenu = storeMenuRepository.findById(storeMenuDTO.getSeq()).get();


        try {
            checkMenu(storeMenuDTO, principal, storeMenu.getStoreCategory().getStore()); // request 상태 체크
        } catch (NullPointerException e) {
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_INFO); //존재하지 않는 정보
        }

        if (storeMenu == null)
            throw new StoreException(StoreExceptionEnum.DOES_NOT_EXIST_MENU);

        List<StoreMS> storeMSs = new ArrayList<>();

        if (storeMenuDTO.getStoreMSs() != null)
            storeMSs = StoreMapper.instance.storeMSDTOToStoreMS(storeMenuDTO.getStoreMSs());

        List<StoreMenuImg> deleteMenuImgs = new ArrayList<>(); //삭제할 이미지 담아주는곳
        List<StoreMenuImg> storeMenuImgList = new ArrayList<>(); // 메뉴가 원래 가지고 있는 이미지

        for (int i = 0; i < storeMenuDTO.getStoreMenuImgs().size(); i++) {
            if (storeMenuDTO.getStoreMenuImgs().get(i).getStatus() != null
                    && storeMenuDTO.getStoreMenuImgs().get(i).getStatus().equals("false")) {
                deleteMenuImgs.add(StoreMapper.instance.storeMenuImgDTOToStoreMenuImg2(storeMenuDTO.getStoreMenuImgs().get(i)));
            } else {
                storeMenuImgList.add(StoreMapper.instance.storeMenuImgDTOToStoreMenuImg2(storeMenuDTO.getStoreMenuImgs().get(i)));
            }
        }

        if (deleteMenuImgs.size() != 0) //메뉴 이미지 삭제
            imgService.deleteMenuImg(deleteMenuImgs);

        List<StoreMenuImg> storeMenuImgs; //새로 저장하는 이미지
        List<StoreMenuImg> totalMenuImg = new ArrayList<>();
        totalMenuImg.addAll(storeMenuImgList); //기존 이미지는 그냥 넣고
        if (imgFile != null) { // 이미지가 존재하는 경우
            storeMenuImgs = imgService.saveMenuImg(imgFile, storeMenu.getStoreCategory().getStore());
            totalMenuImg.addAll(storeMenuImgs); // 새로운 이미지 있으면 그뒤에 추가
        }

        try {

            storeMenu = storeMenuRepository.save(StoreMenu.builder()
                    .seq(storeMenuDTO.getSeq())
                    .price(storeMenuDTO.getPrice())
                    .name(storeMenuDTO.getName())
                    .status(storeMenuDTO.isStatus())
                    .storeMSs(storeMSs)
                    .storeCategorySeq(storeMenuDTO.getStoreCategorySeq())
                    .soldOutStatus(storeMenuDTO.isSoldOutStatus())
                    .storeMenuImgs(totalMenuImg)
                    .build());

        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_UPDATE_MENU);
        }

        StoreMenuDTO resStoreMenuUpdate = StoreMapper.instance.storeMenuToStoreMenuDTO(storeMenu);


        return ApiResponseDTO.builder()
                .message("메뉴 수정")
                .detail("메뉴를 수정했습니다.")
                .data(resStoreMenuUpdate)
                .build();
    }

    @Transactional
    public ApiResponseDTO deleteMenu(StoreMenuDTO storeMenuDTO, Principal principal) {

        StoreMenu storeMenu = storeMenuRepository.findById(storeMenuDTO.getSeq()).orElse(null);

        try {
            checkMenu(storeMenuDTO, principal, storeMenu.getStoreCategory().getStore()); // request 상태 체크

            if (storeMenu.getStoreMenuImgs().size() != 0)
                imgService.deleteMenuImg(storeMenu.getStoreMenuImgs());

            storeMenuRepository.deleteById(storeMenu.getSeq());
        } catch (NullPointerException e) {
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_INFO); //존재하지 않는 정보
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_DELETE_MENU); //메뉴 삭제 실패
        }

        return ApiResponseDTO.builder()
                .message("메뉴 삭제")
                .detail("메뉴를 삭제했습니다.")
                .data(true)
                .build();
    }


    public ApiResponseDTO saveStoreQRInfo(QRStoreInfoDTO qrStoreInfoDTO) {

        QRStoreInfo qrStoreInfo = qrStoreInfoRepository.findByStoreSeq(qrStoreInfoDTO.getStoreSeq());

        if (qrStoreInfo != null && qrStoreInfoDTO.getSeq() == null)
            throw new AdminException(AdminExceptionEnum.ALREADY_STORE_QR_INFO);

        QRStoreInfoDTO resQRStoreInfoDTO;

        try {

            qrStoreInfo = QRStoreInfo.builder()
                    .seq(qrStoreInfoDTO.getSeq())
                    .storeSeq(qrStoreInfoDTO.getStoreSeq())
                    .qrPayMoney(qrStoreInfoDTO.getQrPayMoney())
                    .qrPayDate(qrStoreInfoDTO.getQrPayDate())
                    .qrSubscribeSeq(qrStoreInfoDTO.getQrSubscribeSeq())
                    .qrSize(qrStoreInfoDTO.getQrSize())
                    .qrPayStatus(qrStoreInfoDTO.isQrPayStatus())
                    .build();

            resQRStoreInfoDTO = StoreMapper.instance.qrStoreInfoToQRStoreInfoDTO(qrStoreInfoRepository.save(qrStoreInfo));
        } catch (Exception e) {
            throw new AdminException(AdminExceptionEnum.STORE_QR_INFO_FAIL);
        }


        return ApiResponseDTO.builder()
                .message("가맹점 QR 정보 저장 성공")
                .detail("가맹점의 QR 정보를 저장했습니다.")
                .data(resQRStoreInfoDTO)
                .build();
    }

    public ApiResponseDTO saveStoreQRId(String id, Long storeSeq, Principal principal) {
        Store store = storeRepository.findById(storeSeq).orElse(null);

        if (store == null) //가맹점이 없을 경우
            throw new StoreException(StoreExceptionEnum.CAN_NOT_FOUND_STORE);

        for (StoreQRId storeQRId : store.getStoreQRIds()) {
            if (storeQRId.getQrId().equals(id))
                throw new StoreException(StoreExceptionEnum.CAN_NOT_OVERLAPPING_QR_ID);
        }

        User user = store.getUser();

        if (!user.getEmail().equals(principal.getName())) //유저가 일치하지 않는 경우
            throw new StoreException(StoreExceptionEnum.AUTH_NOT_MATCH);

        if (store.getQrStoreInfo().getQrSize() < store.getStoreQRIds().size())
            throw new StoreException(StoreExceptionEnum.QR_SIZE_OVER);

        StoreQRId storeQRId = StoreQRId.builder()
                .storeSeq(storeSeq)
                .qrId(id)
                .build();

        List<StoreQRId> storeQRIds = store.getStoreQRIds();

        storeQRIds.add(storeQRId);

        store.setStoreQRIds(storeQRIds);

        StoreDTO storeDTO = StoreMapper.instance.storeToStoreDTO(storeRepository.save(store));

        return ApiResponseDTO.builder()
                .message("qr 정보 저장")
                .detail("QR 정보를 저장했습니다.")
                .data(storeDTO)
                .build();
    }

    public ApiResponseDTO saveSideCategory(StoreSideCategoryDTO storeSideCategoryDTO, Principal principal) {

        User user = userRepository.findByEmail(principal.getName());

        checkStoreSize(user.getStores()); //가게가 존재하는지 먼저 체크

        for (int i = 0; i < user.getStores().size(); i++) {
            if (user.getStores().get(i).getSeq() == storeSideCategoryDTO.getStoreSeq()) {

                user.getStores().get(i).checkStoreStatus(user.getStores().get(i).getStatus()); //가맹점 상태가 어떤지 확인

                try {
                    StoreSideCategory storeSideCategory = StoreMapper.instance.storeSideCategoryDTOToStoreSideCategory(storeSideCategoryDTO);
                    return ApiResponseDTO.builder()
                            .message("사이드 카테고리 저장 성공")
                            .detail("사이드 카테고리를 저장하였습니다.")
                            .data(storeSideCategoryRepository.save(storeSideCategory))
                            .build();
                } catch (Exception e) {
                    throw new StoreException(StoreExceptionEnum.FAIL_SAVE_CATEGORY);
                }
            }
        }
        throw new StoreException(StoreExceptionEnum.FAIL_SAVE_CATEGORY);
    }

    public ApiResponseDTO updateSideCategory(StoreSideCategoryDTO storeSideCategoryDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        checkStoreSize(user.getStores()); //가게가 존재하지 않을 경우

        List<StoreSideCategory> storeSideCategories = findStoreSideCategory(user, storeSideCategoryDTO);

        checkSideCategorySize(storeSideCategories); //가져온 가맹점의 카테고리가 존재하는지 확인하는 부분

        try {
            for (int i = 0; i < storeSideCategories.size(); i++) {
                if (storeSideCategories.get(i).getSeq() == storeSideCategoryDTO.getSeq()) { // 변경해야될 카테고리 부분을 찾았다면?
                    storeSideCategories.get(i).setName(storeSideCategoryDTO.getName());
                    storeSideCategories.get(i).setStatus(storeSideCategoryDTO.isStatus());
                    return ApiResponseDTO.builder()
                            .message("카테고리 수정")
                            .detail("카테고리를 수정했습니다.")
                            .data(StoreMapper.instance.storeSideCategoryToStoreSideCategoryDTO(storeSideCategoryRepository.save(storeSideCategories.get(i))))
                            .build();
                }
            }
            throw new StoreException(StoreExceptionEnum.FAIL_UPDATE_CATEGORY);
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.FAIL_UPDATE_CATEGORY);
        }

    }

    public ApiResponseDTO deleteSideCategory(StoreSideCategoryDTO storeSideCategoryDTO, Principal principal) {

        User user = userRepository.findByEmail(principal.getName());

        checkStoreSize(user.getStores()); //가게가 존재하지 않을 경우

        List<StoreSideCategory> storeSideCategories = findStoreSideCategory(user, storeSideCategoryDTO);

        checkSideCategorySize(storeSideCategories); //가져온 가맹점의 카테고리가 존재하는지 확인하는 부분

        try {
            for (int i = 0; i < storeSideCategories.size(); i++) {
                if (storeSideCategories.get(i).getSeq() == storeSideCategoryDTO.getSeq()) { //삭제할 카테고리를 찾으면?
                    storeSideCategoryRepository.delete(storeSideCategories.get(i)); //카테고리 삭제
                    return ApiResponseDTO.builder()
                            .message("사이드 카테고리 삭제")
                            .detail("사이드 카테고리를 삭제하였습니다.")
                            .data(storeSideCategoryDTO)
                            .build();
                }
            }
            throw new StoreException(StoreExceptionEnum.DOES_NOT_EXIST_CATEGORY);
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_DELETE_CATEGORY);
        }
    }


    public ApiResponseDTO saveSideMenu(StoreSideMenuDTO storeSideMenuDTO, List<MultipartFile> imgFile, Principal principal) {

        StoreSideCategory storeSideCategory = storeSideCategoryRepository.findById(storeSideMenuDTO.getStoreSideCategorySeq()).orElse(null);

        try {
            checkSideMenu(storeSideMenuDTO, principal, storeSideCategory.getStore()); // request 상태 체크
        } catch (NullPointerException e) {
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_INFO); //존재하지 않는 정보
        }

        List<StoreSideImg> storeSideImgs = null;

        if (imgFile != null) // 이미지가 존재하는 경우
            storeSideImgs = imgService.saveSideMenuImg(imgFile, storeSideCategory.getStore());

        StoreSideMenu storeSideMenu = StoreMapper.instance.storeSideMenuDTOToStoreSideMenu(storeSideMenuDTO);

        storeSideMenu.setStoreSideImgs(storeSideImgs);

        try {
            storeSideMenu = storeSideMenuRepository.save(storeSideMenu);
        } catch (Exception e) {
            if (imgFile != null)
                imgService.deleteSideMenuImg(storeSideImgs);
            throw new StoreException(StoreExceptionEnum.CAN_NOT_SAVE_MENU);
        }


        return ApiResponseDTO.builder()
                .message("사이드 메뉴 저장")
                .detail(storeSideMenu.getName() + "사이드 메뉴를 저장했습니다.")
                .data(storeSideMenu)
                .build();
    }

    public ApiResponseDTO updateSideMenu(StoreSideMenuDTO storeSideMenuDTO, List<MultipartFile> imgFile, Principal principal) {

        StoreSideMenu storeSideMenu = storeSideMenuRepository.findById(storeSideMenuDTO.getSeq()).get();

        try {
            checkSideMenu(storeSideMenuDTO, principal, storeSideMenu.getStoreSideCategory().getStore()); // request 상태 체크
        } catch (NullPointerException e) {
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_INFO); //존재하지 않는 정보
        }

        if (storeSideMenu == null)
            throw new StoreException(StoreExceptionEnum.DOES_NOT_EXIST_SIDE_MENU);

        try {

            List<StoreSideImg> deleteStoreSideImg = new ArrayList<>();
            List<StoreSideImg> storeSideImgList = new ArrayList<>(); // 메뉴가 원래 가지고 있는 이미지

            for (int i = 0; i < storeSideMenuDTO.getStoreSideImgs().size(); i++) {
                if (storeSideMenuDTO.getStoreSideImgs().get(i).getStatus() != null
                        && storeSideMenuDTO.getStoreSideImgs().get(i).getStatus().equals("false")) { //사이드 이미지가 삭제해야한다면?
                    deleteStoreSideImg.add(StoreMapper.instance.storeSideImgDTOToStoreSideImg(storeSideMenuDTO.getStoreSideImgs().get(i))); //삭제할 이미지들 담음
                } else {
                    storeSideImgList.add(StoreMapper.instance.storeSideImgDTOToStoreSideImg(storeSideMenuDTO.getStoreSideImgs().get(i))); // 기존에 유지하는 이미지 담음
                }
            }

            if (deleteStoreSideImg.size() != 0)
                imgService.deleteSideMenuImg(deleteStoreSideImg); //사이드 이미지 삭제함

            List<StoreSideImg> storeSideImgs; //새로 저장하는 이미지
            List<StoreSideImg> totalMenuImg = new ArrayList<>(); //전체 이미지
            totalMenuImg.addAll(storeSideImgList); //기존 이미지는 그냥 넣고
            if (imgFile != null) { // 이미지가 존재하는 경우
                storeSideImgs = imgService.saveSideMenuImg(imgFile, storeSideMenu.getStoreSideCategory().getStore());
                totalMenuImg.addAll(storeSideImgs); // 새로운 이미지 있으면 그뒤에 추가
            }

            storeSideMenu = storeSideMenuRepository.save(storeSideMenu.builder()
                    .seq(storeSideMenuDTO.getSeq())
                    .price(storeSideMenuDTO.getPrice())
                    .name(storeSideMenuDTO.getName())
                    .status(storeSideMenuDTO.isStatus())
                    .storeSideCategorySeq(storeSideMenuDTO.getStoreSideCategorySeq())
                    .soldOutStatus(storeSideMenuDTO.isSoldOutStatus())
                    .storeSideImgs(totalMenuImg)
                    .build());

            StoreSideMenuDTO resStoreSideMenuUpdate = StoreMapper.instance.storeSideMenuToStoreSideMenuDTO(storeSideMenu);

            return ApiResponseDTO.builder()
                    .message("사이드 메뉴 수정")
                    .detail("사이드 메뉴를 수정했습니다.")
                    .data(resStoreSideMenuUpdate)
                    .build();

        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_UPDATE_MENU);
        }

    }

    public ApiResponseDTO deleteSideMenu(StoreSideMenuDTO storeSideMenuDTO, Principal principal) {
        StoreSideMenu storeSideMenu = storeSideMenuRepository.findById(storeSideMenuDTO.getSeq()).orElse(null);

        try {
            checkSideMenu(storeSideMenuDTO, principal, storeSideMenu.getStoreSideCategory().getStore()); // request 상태 체크

            if (storeSideMenu.getStoreSideImgs().size() != 0)
                imgService.deleteSideMenuImg(storeSideMenu.getStoreSideImgs());

            storeSideMenuRepository.deleteById(storeSideMenu.getSeq());
        } catch (NullPointerException e) {
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_INFO); //존재하지 않는 정보
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_DELETE_MENU); //메뉴 삭제 실패
        }

        return ApiResponseDTO.builder()
                .message("사이드 메뉴 삭제")
                .detail("사이드 메뉴를 삭제했습니다.")
                .data(true)
                .build();
    }


    // ----------------------- 함수 -----------------------------

    public List<StoreCategory> findStoreCategory(User user, StoreCategoryDTO storeCategoryDTO) { // 해당되는 가맹점의 카테고리를 가져오는 함수
        for (int i = 0; i < user.getStores().size(); i++) {
            if (user.getStores().get(i).getSeq() == storeCategoryDTO.getStoreSeq()) {
                user.getStores().get(i).checkStoreStatus(user.getStores().get(i).getStatus()); //가맹점 상태가 활성화 되어있는지 확인
                return user.getStores().get(i).getStoreCategories(); // 해당되는 가맹점의 카테고리를 가져옴
            }
        }
        throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_CATEGORY);
    }

    public List<StoreSideCategory> findStoreSideCategory(User user, StoreSideCategoryDTO storeSideCategoryDTO) {
        for (int i = 0; i < user.getStores().size(); i++) {
            if (user.getStores().get(i).getSeq() == storeSideCategoryDTO.getStoreSeq()) {
                user.getStores().get(i).checkStoreStatus(user.getStores().get(i).getStatus()); //가맹점 상태가 활성화 되어있는지 확인
                return user.getStores().get(i).getStoreSideCategories(); // 해당되는 가맹점의 카테고리를 가져옴
            }
        }
        throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_CATEGORY);
    }

    public void checkStoreSize(List<Store> store) {
        if (store == null || store.size() == 0)
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE); //존재 하지 않는 가게
    }

    public void checkCategorySize(List<StoreCategory> storeCategories) { //가맹점의 카테고리가 존재하는지 확인
        if (storeCategories == null || storeCategories.size() == 0)
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_CATEGORY);
    }

    public void checkSideCategorySize(List<StoreSideCategory> storeSideCategories) { //가맹점의 카테고리가 존재하는지 확인
        if (storeSideCategories == null || storeSideCategories.size() == 0)
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_SIDE_CATEGORY);
    }


    private Store checkMenu(StoreMenuDTO storeMenuDTO, Principal principal, Store store) {

        StoreCategory storeCategory = null;

        for (int i = 0; i < store.getStoreCategories().size(); i++) {
            if (storeMenuDTO.getStoreCategorySeq().equals(store.getStoreCategories().get(i).getSeq()))
                storeCategory = store.getStoreCategories().get(i);
        }

        if (storeCategory == null)
            throw new StoreException(StoreExceptionEnum.DOES_NOT_EXIST_CATEGORY); //존재하지 않은 카테고리

        if (!storeCategory.getStore().getUser().getEmail().equals(principal.getName()))
            throw new ApiException(ExceptionEnum.AUTHORIZATION_FAILED_REQUEST); //허용되지 않은 접근

        store.checkStoreStatus(store.getStatus()); // 가맹점 상태 체크


        return store;
    }

    private Store checkSideMenu(StoreSideMenuDTO storeSideMenuDTO, Principal principal, Store store) {

        StoreSideCategory storeSideCategory = null;

        for (int i = 0; i < store.getStoreSideCategories().size(); i++) {
            if (storeSideMenuDTO.getStoreSideCategorySeq() == store.getStoreSideCategories().get(i).getSeq())
                storeSideCategory = store.getStoreSideCategories().get(i);
        }

        if (storeSideCategory == null)
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_SIDE_CATEGORY); //존재하지 않은 카테고리

        if (!storeSideCategory.getStore().getUser().getEmail().equals(principal.getName()))
            throw new ApiException(ExceptionEnum.AUTHORIZATION_FAILED_REQUEST); //허용되지 않은 접근

        store.checkStoreStatus(store.getStatus()); // 가맹점 상태 체크

        return store;
    }

    public ApiResponseDTO menuConnectSideService(List<StoreMSDTO> storeMSDTOS) {
        List<StoreMS> storeMS = StoreMapper.instance.storeMSDTOToStoreMS(storeMSDTOS);
        storeMSRepository.saveAll(storeMS);
        return ApiResponseDTO.builder()
                .message("메뉴에 사이드 추가")
                .detail("메뉴에 사이드를 추가하였습니다.")
                .data(true)
                .build();
    }

}
