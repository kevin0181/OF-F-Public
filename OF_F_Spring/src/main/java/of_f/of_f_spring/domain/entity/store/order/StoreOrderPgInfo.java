package of_f.of_f_spring.domain.entity.store.order;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Store_Order_Pg_Info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderPgInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Order_seq")
    private Long storeOrderSeq;

    @Column(name = "imp_uid")
    private String impUid;

    @Column(name = "merchant_uid")
    private String merchantUid;

    @Column(name = "pg_provider")
    private String pgProvider;

    @Column(name = "pay_method")
    private String payMethod;

    @Column(name = "status")
    private String status;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_number")
    private String cardNumber;

    @OneToOne
    @JoinColumn(name = "Store_Order_seq", insertable = false, updatable = false)
    private StoreOrder storeOrder;

}
