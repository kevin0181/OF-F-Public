package of_f.of_f_spring.dto.store.qr;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QRStoreInfoDTO {
    private Long seq;
    private Long storeSeq;
    private Long qrSubscribeSeq;
    private int qrSize;
    private String qrPayMoney;
    private boolean qrPayStatus;
    private String qrPayDate;
}
