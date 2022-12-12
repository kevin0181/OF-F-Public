package of_f.of_f_spring.domain.entity.store.qr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import of_f.of_f_spring.domain.entity.store.Store;

import javax.persistence.*;

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

    @Column
    private String id;

    @ManyToOne
    @JoinColumn(name = "Store_seq", insertable = false, updatable = false)
    private Store store;
}
