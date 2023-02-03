package of_f.of_f_spring.domain.entity.store.order;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.domain.entity.user.User;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @Column(name = "Store_QR_ID")
    private String storeQRId;

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
    private int place;

    @Column
    private int status;

    @Column(name = "pay_status")
    private int payStatus;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "email_receive_status")
    private Boolean emailReceiveStatus;

    @Column(name = "phone_number_receive_status")
    private Boolean phoneNumberReceiveStatus;


    @OneToMany
    @JoinColumn(name = "Store_Order_seq")
    private List<StoreOrderMenu> storeOrderMenus;

    @OneToOne(mappedBy = "storeOrder")
    private StoreOrderPgInfo storeOrderPgInfo;

    @OneToOne(mappedBy = "storeOrder")
    private StoreOrderVanInfo storeOrderVanInfo;

    @ManyToOne
    @JoinColumn(name = "Store_seq", insertable = false, updatable = false)
    private Store store;

//    @ManyToOne
//    @JoinColumn(name = "Store_QR_ID", insertable = false, updatable = false)
//    private StoreQRId storeQRId;

}
