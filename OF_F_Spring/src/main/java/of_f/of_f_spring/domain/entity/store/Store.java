package of_f.of_f_spring.domain.entity.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.menu.StoreCategory;
import of_f.of_f_spring.domain.entity.store.menu.StoreSideCategory;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.domain.entity.user.User;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Store extends StoreStatusCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "User_seq")
    private Long userSeq;

    @Column
    private String name;

    @Column(name = "business_number")
    private String businessNumber;

    @Column
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "account_bank_name")
    private String accountBankName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "deposit_date")
    private String depositDate;

    @Column(name = "business_phone_number")
    private String businessPhoneNumber;

    @Column(name = "open_date")
    private String openDate;

    @Column
    private int status;

    @Column(name = "apply_receive_status")
    private boolean applyReceiveStatus;

    @ManyToOne
    @JoinColumn(name = "User_seq", insertable = false, updatable = false)
    private User user;

    @OneToOne(mappedBy = "store")
    private QRStoreInfo qrStoreInfo;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StoreOrder> storeOrders;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StorePgSetting> storePgSettings;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StoreVanSetting> storeVanSettings;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StoreCategory> storeCategories;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Store_seq")
    private List<StoreQRId> storeQRIds;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StoreSideCategory> storeSideCategories;


}
