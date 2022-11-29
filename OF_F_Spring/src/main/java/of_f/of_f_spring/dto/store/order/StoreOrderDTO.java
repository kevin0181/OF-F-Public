package of_f.of_f_spring.dto.store.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class StoreOrderDTO {
    private Long seq;
    private Long storeSeq;
    private Long userSeq;
    private String id;
    private Long kind;
    private String orderNumber;
    private String totalPrice;
    private String cancelAfterPrice;
    private String date;
    private Long place;
    private Long status;
    private Long payStatus;

    private OrderUserDTO user;
    private StoreOrderPgInfoDTO storeOrderPgInfo;
    private StoreOrderVanInfoDTO storeOrderVanInfo;
    private List<StoreOrderMenuDTO> storeOrderMenus;

}
