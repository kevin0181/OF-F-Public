package of_f.of_f_spring.dto.store.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreSideMenuDTO {
    private Long seq;
    private Long storeSideCategorySeq;
    private String name;
    private String price;
    private boolean status;
    private boolean soldOutStatus;
    private List<StoreSideImgDTO> storeSideImgs;
//    private StoreOrderSideDTO storeOrderSide;
}
