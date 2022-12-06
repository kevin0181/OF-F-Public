package of_f.of_f_spring.domain.mapper.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.menu.StoreCategory;
import of_f.of_f_spring.domain.entity.store.menu.StoreMS;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenu;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.StoreCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreMSDTO;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface StoreMapper {
    StoreMapper instance = Mappers.getMapper(StoreMapper.class);

    Store storeDTOTOStore(StoreDTO storeDTO);

    StoreCategory storeCategoryDTOToStoreCategory(StoreCategoryDTO storeCategoryDTO);

    StoreMenu storeMenuToStoreMenuDTO(StoreMenuDTO storeMenuDTO);

    List<StoreMS> storeMSDTOToStoreMS(List<StoreMSDTO> storeMSDTOS);

    StoreMenuDTO storeMenuToStoreMenuDTO(StoreMenu storeMenu);

    StoreDTO storeToStoreDTO(Store store);

}
