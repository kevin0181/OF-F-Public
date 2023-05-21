package of_f.of_f_spring.domain.entity.store.qr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Store_QR_ID")
public class StoreQRId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_seq")
    private Long storeSeq;

    @Column(name = "QR_Id")
    private String qrId;

    @ManyToOne
    @JoinColumn(name = "Store_seq", insertable = false, updatable = false)
    private Store store;

//    @OneToMany
//    @JoinColumn(name = "Store_QR_ID_seq")
//    private List<StoreOrder> storeOrderList;

}
