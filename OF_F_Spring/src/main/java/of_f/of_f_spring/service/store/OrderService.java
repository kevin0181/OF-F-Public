package of_f.of_f_spring.service.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.domain.exception.OrderException;
import of_f.of_f_spring.domain.exception.OrderExceptionEnum;
import of_f.of_f_spring.domain.mapper.store.OrderMapper;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.dto.total.StoreName;
import of_f.of_f_spring.repository.store.StoreMenuRepository;
import of_f.of_f_spring.repository.store.StoreOrderRepository;
import of_f.of_f_spring.repository.store.StoreQRIdRepository;
import of_f.of_f_spring.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreOrderRepository storeOrderRepository;

    @Autowired
    private StoreQRIdRepository storeQRIdRepository;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @Transactional
    public ApiResponseDTO getStoreMenuList(Long storeSeq, String qrId) {
        Store store = storeRepository.findById(storeSeq).orElse(null);

        for (int i = 0; i < store.getStoreQRIds().size(); i++) {
            if (store.getStoreQRIds().get(i).getQrId().equals(qrId)) {
                StoreDTO storeDTO = StoreMapper.instance.storeToStoreDTOByOrderUser(store);
                return ApiResponseDTO.builder()
                        .message("가맹점 메뉴 정보")
                        .detail("가맹점 메뉴 정보 입니다.")
                        .data(storeDTO)
                        .build();
            }
        }

        throw new OrderException(OrderExceptionEnum.DOES_NOT_EXIST_QR_ID);
    }

    public ApiResponseDTO searchStore(String storeName) {
        List<Store> storeList = storeRepository.findAllByNameContaining(storeName);

        List<StoreName> storeNames = StoreMapper.instance.storeToStoreName(storeList);

        return ApiResponseDTO.builder()
                .message("가게 정보")
                .detail("")
                .data(storeNames)
                .build();
    }

//    public void checkAmount(StoreOrderDTO storeOrderDTO) {
//
//        int price = 0; //가격
//        List<Long> storeMenuId = new ArrayList<>();
//
//        for (int i = 0; i < storeOrderDTO.getStoreOrderMenus().size(); i++) {
//            storeMenuId.add(storeOrderDTO.getStoreOrderMenus().get(i).getStoreMenuSeq());
//        }
//        List<StoreMenu> storeMenus = storeMenuRepository.findAllById(storeMenuId);
//        List<StoreSideCategory> storeSideCategories = new ArrayList<>();
//
//        for (int i = 0; i < storeMenus.size(); i++) {
//            for (int j = 0; j < storeOrderDTO.getStoreOrderMenus().size(); j++) {
//                if (storeMenus.get(i).getSeq().equals(storeOrderDTO.getStoreOrderMenus().get(j).getStoreMenuSeq())) {
//                    price += Integer.parseInt(storeMenus.get(i).getPrice()) * storeOrderDTO.getStoreOrderMenus().get(j).getSize(); //메뉴 * 메뉴 사이즈
//                }
//            }
//        }
//
//    }

    public ApiResponseDTO beforeSaveData(StoreOrderDTO storeOrderDTO) {

        String merchant_uid = randomOrderId();
        storeOrderDTO.setId(merchant_uid); //주문번호 선 저장
        storeOrderDTO.setStatus(0);
        storeOrderDTO.setPayStatus(0);
        storeOrderDTO.setDate(null);

        return ApiResponseDTO.builder()
                .message("주문 결제 전 데이터")
                .detail("")
                .data(storeOrderRepository.save(StoreMapper.instance.storeOrderDTOToStoreOrder(storeOrderDTO)))
                .build();
    }

    public String randomOrderId() {

        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmm");
        String result = df.format(today);

        return result + "_" + temp;
    }

}
