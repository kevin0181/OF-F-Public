package of_f.of_f_spring.domain.entity.store.order;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.user.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Order")
public class StoreOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_seq")
    private Long storeSeq;

    @Column(name = "User_seq")
    private Long userSeq;

    @Column
    private String id;

    @Column
    private Long kind;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "cancel_after_price")
    private String cancelAfterPrice;

    @Column
    private String date;

    @Column
    private Long place;

    @Column
    private Long status;

    @Column(name = "pay_status")
    private Long payStatus;

    @ManyToOne
    @JoinColumn(name = "User_seq", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Store_seq", insertable = false, updatable = false)
    private Store store;

    @OneToOne(mappedBy = "storeOrder")
    private StoreOrderPgInfo storeOrderPgInfo;

    @OneToOne(mappedBy = "storeOrder")
    private StoreOrderVanInfo storeOrderVanInfo;

    @OneToMany
    @JoinColumn(name = "Store_Order_seq")
    private List<StoreOrderMenu> storeOrderMenus;

}
