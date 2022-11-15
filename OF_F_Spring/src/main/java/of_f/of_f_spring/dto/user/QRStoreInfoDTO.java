package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QRStoreInfoDTO {
    private Long seq;
    private Long storeSeq;
    private Long qrSubscribeSeq;
    private int qrSize;
    private String qrPayMoney;
    private String qrPayStatus;
    private String qrPayDate;
}
