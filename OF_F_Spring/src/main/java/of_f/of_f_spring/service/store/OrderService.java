package of_f.of_f_spring.service.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.domain.mapper.store.OrderMapper;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.repository.store.StoreOrderRepository;
import of_f.of_f_spring.repository.store.StoreQRIdRepository;
import of_f.of_f_spring.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreOrderRepository storeOrderRepository;

    @Autowired
    private StoreQRIdRepository storeQRIdRepository;

    @Transactional
    public ApiResponseDTO getStoreMenuList(Long storeSeq) {
        Store store = storeRepository.findById(storeSeq).orElse(null);
        StoreDTO storeDTO = StoreMapper.instance.storeToStoreDTOByOrderUser(store);
        return ApiResponseDTO.builder()
                .message("가맹점 메뉴 정보")
                .detail("가맹점 메뉴 정보 입니다.")
                .data(storeDTO)
                .build();
    }

    public ApiResponseDTO orderQRService(String qrId, StoreOrderDTO storeOrderDTO) {

        StoreQRId storeQRId = storeQRIdRepository.findByQrId(qrId);

        StoreOrder storeOrder = OrderMapper.instance.storeOrderDTOToStoreOrder(storeOrderDTO);

        storeOrder.setStoreQRIdSeq(storeQRId.getSeq());

        StoreOrderDTO resStoreOrderDTO =
                OrderMapper.instance.storeOrderToStoreOrderDTO(storeOrderRepository.save(storeOrder));

        return ApiResponseDTO.builder()
                .message("주문 성공")
                .detail("주문이 완료되었습니다.")
                .data(resStoreOrderDTO)
                .build();
    }


}
