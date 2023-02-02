package of_f.of_f_spring.dto.store.order;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.dto.store.menu.StoreSideMenuDTO;

import javax.persistence.*;

@Getter
@Setter
public class StoreOrderSideDTO {
    private Long seq;
    private Long storeSideMenuSeq;
    private Long storeOrderMenuSeq;

    private StoreSideMenuDTO storeSideMenu;

}
