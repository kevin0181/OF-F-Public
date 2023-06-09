package of_f.of_f_spring.domain.entity.store.qr;

import lombok.*;
import of_f.of_f_spring.domain.entity.store.Store;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean qrPayStatus;

    @Column(name = "QR_pay_date")
    private String qrPayDate;

    @OneToOne
    @JoinColumn(name = "Store_seq", insertable = false, updatable = false)
    private Store store;

}
