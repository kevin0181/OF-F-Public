package of_f.of_f_spring.dto.store.order;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;

import java.util.List;

@Getter
@Setter
public class StoreOrderMenuDTO {

    private Long seq;
    private Long storeMenuSeq;
    private Long storeOrderSeq;
    private int size;

    private List<StoreOrderSideDTO> storeOrderSides;
    private StoreMenuDTO storeMenu;
}
