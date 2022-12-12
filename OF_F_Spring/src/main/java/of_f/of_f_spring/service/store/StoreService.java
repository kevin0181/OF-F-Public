package of_f.of_f_spring.service.store;

import lombok.extern.slf4j.Slf4j;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.menu.StoreCategory;
import of_f.of_f_spring.domain.entity.store.menu.StoreMS;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenu;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenuImg;
import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.*;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.domain.mapper.user.UserMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.StoreCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.repository.store.QRStoreInfoRepository;
import of_f.of_f_spring.repository.store.StoreCategoryRepository;
import of_f.of_f_spring.repository.store.StoreMenuRepository;
import of_f.of_f_spring.repository.store.StoreRepository;
import of_f.of_f_spring.repository.user.UserRepository;
import of_f.of_f_spring.service.config.ImgService;
import of_f.of_f_spring.service.user.EmailService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @Autowired
    private QRStoreInfoRepository qrStoreInfoRepository;

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
        }

        if (userDTO.getStores().size() == 0) //모든 가맹점이 비활성화 여서 하나도 없을 시.
            throw new StoreException(StoreExceptionEnum.WAIT_STORE_RESPONSE);

        return ApiResponseDTO.builder()
                .message("가맹점 정보")
                .detail("가맹점의 정보 입니다.")
                .data(userDTO)
                .build();
    }


//    public ApiResponseDTO getCategoryList(StoreCategoryDTO storeCategoryDTO) { //카테고리 리스트 가져오기
//
//        if (storeCategoryDTO == null || storeCategoryDTO.getSeq() == null || storeCategoryDTO.getSeq().equals("")) { //전체 리스트 가져오기
//            return ApiResponseDTO.builder()
//                    .message("카테고리 전체 가져오기")
//                    .detail("카테고리 전체 가져오기")
//                    .data(StoreMapper.instance.storeCategoryToStoreCategoryDTOList(storeCategoryRepository.findAll()))
//                    .build();
//        } else { //특정 카테고리 리스트 가져오기
//            return ApiResponseDTO.builder()
//                    .message("카테고리 가져오기")
//                    .detail("카테고리 가져오기")
//                    .data(StoreMapper.instance.storeCategoryToStoreCategoryDTO(storeCategoryRepository.findById(storeCategoryDTO.getSeq()).orElse(null)))
//                    .build();
//        }
//    }

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
                if (storeCategoryList.get(i).getSeq() == storeCategoryDTO.getSeq()) { // 변경해야될 카테고리 부분을 찾았다면?
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
                if (storeCategoryList.get(i).getSeq() == storeCategoryDTO.getSeq()) { //삭제할 카테고리를 찾으면?
                    storeCategoryRepository.delete(storeCategoryList.get(i)); //카테고리 삭제
                    return ApiResponseDTO.builder()
                            .message("카테고리 삭제")
                            .detail("카테고리를 삭제하였습니다.")
                            .data(true)
                            .build();
                }
            }
            throw new StoreException(StoreExceptionEnum.DOES_NOT_EXIST_CATEGORY);
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_DELETE_CATEGORY);
        }
    }

    public void checkStoreSize(List<Store> store) {
        if (store == null || store.size() == 0)
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE); //존재 하지 않는 가게
    }

    public void checkCategorySize(List<StoreCategory> storeCategories) { //가맹점의 카테고리가 존재하는지 확인
        if (storeCategories == null || storeCategories.size() == 0)
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_CATEGORY);
    }

    public List<StoreCategory> findStoreCategory(User user, StoreCategoryDTO storeCategoryDTO) { // 해당되는 가맹점의 카테고리를 가져오는 함수
        for (int i = 0; i < user.getStores().size(); i++) {
            if (user.getStores().get(i).getSeq() == storeCategoryDTO.getStoreSeq()) {
                user.getStores().get(i).checkStoreStatus(user.getStores().get(i).getStatus()); //가맹점 상태가 활성화 되어있는지 확인
                return user.getStores().get(i).getStoreCategories(); // 해당되는 가맹점의 카테고리를 가져옴
            }
        }
        throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_CATEGORY);
    }

    @Transactional
    public ApiResponseDTO saveMenu(StoreMenuDTO storeMenuDTO, List<MultipartFile> imgFile, Principal principal) {

        StoreCategory storeCategory = storeCategoryRepository.findById(storeMenuDTO.getStoreCategorySeq()).orElse(null);

        try {
            checkMenu(storeMenuDTO, principal, storeCategory.getStore()); // request 상태 체크
        } catch (NullPointerException e) {
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE_BY_INFO); //존재하지 않는 정보
        }

        List<StoreMenuImg> storeMenuImgs = null;

        if (imgFile != null) // 이미지가 존재하는 경우
            storeMenuImgs = imgService.saveMenuImg(imgFile, storeCategory.getStore());

        StoreMenu storeMenu = StoreMapper.instance.storeMenuToStoreMenuDTO(storeMenuDTO);

        storeMenu.setStoreMenuImgs(storeMenuImgs);

        try {
            storeMenu = storeMenuRepository.save(storeMenu);
        } catch (Exception e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_SAVE_MENU);
        }


        return ApiResponseDTO.builder()
                .message("메뉴 저장")
                .detail(storeMenu.getName() + " 메뉴를 저장했습니다.")
                .data(storeMenu)
                .build();
    }

    @Transactional
    public ApiResponseDTO updateMenu(StoreMenuDTO storeMenuDTO, Principal principal) {

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

        try {

            storeMenu = storeMenuRepository.save(StoreMenu.builder()
                    .seq(storeMenuDTO.getSeq())
                    .price(storeMenuDTO.getPrice())
                    .name(storeMenuDTO.getName())
                    .status(storeMenuDTO.isStatus())
                    .storeMSs(storeMSs)
                    .storeCategorySeq(storeMenuDTO.getStoreCategorySeq())
                    .soldOutStatus(storeMenuDTO.isSoldOutStatus())
                    .storeMenuImgs(storeMenu.getStoreMenuImgs())
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
                imgService.deleteImg(storeMenu.getStoreMenuImgs());

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

    private Store checkMenu(StoreMenuDTO storeMenuDTO, Principal principal, Store store) {

        StoreCategory storeCategory = null;

        for (int i = 0; i < store.getStoreCategories().size(); i++) {
            if (storeMenuDTO.getStoreCategorySeq() == store.getStoreCategories().get(i).getSeq())
                storeCategory = store.getStoreCategories().get(i);
        }

        if (storeCategory == null)
            throw new StoreException(StoreExceptionEnum.DOES_NOT_EXIST_CATEGORY); //존재하지 않은 카테고리

        if (!storeCategory.getStore().getUser().getEmail().equals(principal.getName()))
            throw new ApiException(ExceptionEnum.AUTHORIZATION_FAILED_REQUEST); //허용되지 않은 접근

        store.checkStoreStatus(store.getStatus()); // 가맹점 상태 체크


        return store;
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

        User user = store.getUser();

        if (!user.getEmail().equals(principal.getName())) //유저가 일치하지 않는 경우
            throw new StoreException(StoreExceptionEnum.AUTH_NOT_MATCH);

        StoreQRId storeQRId = StoreQRId.builder()
                .storeSeq(storeSeq)
                .id(id)
                .build();

        List<StoreQRId> storeQRIds = new ArrayList<>();

        storeQRIds.add(storeQRId);

        store.setStoreQRIds(storeQRIds);

        StoreDTO storeDTO = StoreMapper.instance.storeToStoreDTO(storeRepository.save(store));

        return ApiResponseDTO.builder()
                .message("qr 정보 저장")
                .detail("QR 정보를 저장했습니다.")
                .data(storeDTO)
                .build();
    }
}
