package of_f.of_f_spring.dto.store.menu;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.dto.store.order.StoreOrderMenuDTO;

import java.util.List;

@Getter
@Setter
public class StoreMenuDTO {
    private Long seq;
    private Long storeCategorySeq;
    private String name;
    private String price;
    private boolean status;
    private boolean soldOutStatus;

    private List<StoreMenuImgDTO> storeMenuImgs;
    private List<StoreMSDTO> storeMSs;
//    private StoreOrderMenuDTO storeOrderMenu;
}
