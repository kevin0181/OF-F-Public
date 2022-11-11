package of_f.of_f_spring.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "QR_Store_Info")
public class QRStoreInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_seq")
    private Long storeSeq;

    @Column(name = "QR_Subscribe_seq")
    private Long qrSubscribeSeq;

    @Column(name = "QR_size")
    private int qrSize;

    @Column(name = "QR_pay_money")
    private String qrPayMoney;

    @Column(name = "QR_pay_status")
    private String qrPayStatus;

    @Column(name = "QR_pay_date")
    private String qrPayDate;

}
