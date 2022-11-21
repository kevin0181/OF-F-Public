package of_f.of_f_spring.domain.entity.store.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Store_Order_Van_Info")
public class StoreOrderVanInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Order_seq")
    private Long storeOrderSeq;

    @Column(name = "telegramno")
    private String telegramNo;

    @Column(name = "tradetime")
    private String tradeTime;

    @Column(name = "approvalno")
    private String approvalNo;

    @Column(name = "tradeuniqueno")
    private String tradeUniqueNo;

    @Column(name = "card_kind")
    private String cardKind;

    @Column(name = "card_number")
    private String cardNumber;

    @Column
    private String date;

    @OneToOne
    @JoinColumn(name = "Store_Order_seq", insertable = false, updatable = false)
    private StoreOrder storeOrder;

}
