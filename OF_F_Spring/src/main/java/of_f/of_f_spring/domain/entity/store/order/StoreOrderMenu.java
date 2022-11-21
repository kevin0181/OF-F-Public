package of_f.of_f_spring.domain.entity.store.order;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenu;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Order_Menu")
public class StoreOrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Menu_seq")
    private Long storeMenuSeq;

    @Column(name = "Store_Order_seq")
    private Long storeOrderSeq;

    @Column
    private int size;

    @OneToMany
    @JoinColumn(name = "Store_Order_Menu_seq")
    private List<StoreOrderSide> storeOrderSides;

    @ManyToOne
    @JoinColumn(name = "Store_Menu_seq", insertable = false, updatable = false)
    private StoreOrder storeOrder;

    @OneToOne
    @JoinColumn(name = "Store_Menu_seq", insertable = false, updatable = false)
    private StoreMenu storeMenu;

}
