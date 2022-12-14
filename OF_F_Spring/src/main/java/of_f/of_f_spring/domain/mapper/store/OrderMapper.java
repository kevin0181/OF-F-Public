package of_f.of_f_spring.domain.mapper.store;

import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper instance = Mappers.getMapper(OrderMapper.class);

    StoreOrder storeOrderDTOToStoreOrder(StoreOrderDTO storeOrderDTO);

    StoreOrderDTO storeOrderToStoreOrderDTO(StoreOrder storeOrder);
}
