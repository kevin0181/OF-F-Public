package of_f.of_f_spring.domain.entity.store.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Store_Order_Pg_Info")
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

    @OneToOne
    @JoinColumn(name = "Store_Order_seq", insertable = false, updatable = false)
    private StoreOrder storeOrder;

}
