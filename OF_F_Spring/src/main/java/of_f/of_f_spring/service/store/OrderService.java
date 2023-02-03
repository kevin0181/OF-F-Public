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

    public ApiResponseDTO orderQRService(String qrId, StoreOrderDTO storeOrderDTO) {

        StoreQRId storeQRId = storeQRIdRepository.findByQrId(qrId);

        if (storeQRId == null)  // qr id 없음.
            throw new OrderException(OrderExceptionEnum.DOES_NOT_EXIST_QR_ID);


        StoreOrder storeOrder = OrderMapper.instance.storeOrderDTOToStoreOrder(storeOrderDTO);

        storeOrder.setId(randomOrderId());
        storeOrder.setStoreQRId(storeQRId.getQrId());

        StoreOrderDTO resStoreOrderDTO =
                OrderMapper.instance.storeOrderToStoreOrderDTO(storeOrderRepository.save(storeOrder));

        return ApiResponseDTO.builder()
                .message("주문 성공")
                .detail("주문이 완료되었습니다.")
                .data(resStoreOrderDTO)
                .build();
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
        SimpleDateFormat df = new SimpleDateFormat("yyMMdd.HHmm");
        String result = df.format(today);

        return result + "." + temp;
    }

}
