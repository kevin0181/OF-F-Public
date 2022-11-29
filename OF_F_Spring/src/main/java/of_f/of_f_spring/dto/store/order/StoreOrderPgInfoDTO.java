package of_f.of_f_spring.dto.store.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class StoreOrderPgInfoDTO {
    private Long seq;
    private Long storeOrderSeq;
    private String impUid;
    private String merchantUid;
}
