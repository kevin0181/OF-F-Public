package of_f.of_f_spring.service.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private StoreRepository storeRepository;

    public ApiResponseDTO getStoreMenuList(Long storeSeq) {
        Store store = storeRepository.findById(storeSeq).orElse(null);
        StoreDTO storeDTO = StoreMapper.instance.storeToStoreDTOByOrderUser(store);
        return ApiResponseDTO.builder()
                .message("가맹점 메뉴 정보")
                .detail("가맹점 메뉴 정보 입니다.")
                .data(storeDTO)
                .build();
    }
}
