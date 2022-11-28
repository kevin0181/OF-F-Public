package of_f.of_f_spring.domain.entity.store;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.menu.StoreCategory;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.domain.entity.user.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Store {
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

    @ManyToOne
    @JoinColumn(name = "User_seq", insertable = false, updatable = false)
    private User user;

    @OneToOne(mappedBy = "store")
    private QRStoreInfo qrStoreInfo;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StorePgSetting> storePgSettings;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StoreVanSetting> storeVanSettings;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StoreCategory> storeCategories;

    @OneToMany
    @JoinColumn(name = "Store_seq")
    private List<StoreOrder> storeOrders;

}
