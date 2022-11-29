package of_f.of_f_spring.dto.store.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class StoreOrderVanInfoDTO {
    private Long seq;
    private Long storeOrderSeq;
    private String telegramNo;
    private String tradeTime;
    private String approvalNo;
    private String tradeUniqueNo;
    private String cardKind;
    private String cardNumber;
    private String date;
}
