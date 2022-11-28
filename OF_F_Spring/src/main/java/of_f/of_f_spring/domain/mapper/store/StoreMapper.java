package of_f.of_f_spring.domain.mapper.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.dto.store.StoreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {
    StoreMapper instance = Mappers.getMapper(StoreMapper.class);

    Store storeDTOTOStore(StoreDTO storeDTO);
}
