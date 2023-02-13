package of_f.of_f_spring.domain.entity.store.order;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.menu.StoreSideMenu;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Store_Order_Side")
public class StoreOrderSide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Side_Menu_seq")
    private Long storeSideMenuSeq;

    @Column(name = "Store_Order_Menu_seq")
    private Long storeOrderMenuSeq;

    @ManyToOne
    @JoinColumn(name = "Store_Order_Menu_seq", insertable = false, updatable = false)
    private StoreOrderMenu storeOrderMenu;

    @ManyToOne
    @JoinColumn(name = "Store_Side_Menu_seq", insertable = false, updatable = false)
    private StoreSideMenu storeSideMenu;

}
