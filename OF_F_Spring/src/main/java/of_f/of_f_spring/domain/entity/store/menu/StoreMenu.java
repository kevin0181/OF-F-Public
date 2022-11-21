package of_f.of_f_spring.domain.entity.store.menu;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.order.StoreOrderMenu;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Menu")
public class StoreMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Category_seq")
    private Long storeCategorySeq;

    @Column
    private String name;

    @Column
    private String price;

    @Column
    private boolean status;

    @Column(name = "sold_out_status")
    private boolean soldOutStatus;

    @ManyToOne
    @JoinColumn(name = "Store_Category_seq", insertable = false, updatable = false)
    private StoreCategory storeCategory;

    @OneToMany
    @JoinColumn(name = "Store_Menu_seq")
    private List<StoreMenuImg> storeMenuImgs;

    @OneToMany
    @JoinColumn(name = "Store_Menu_seq")
    private List<StoreMS> storeMSs;

    @OneToOne(mappedBy = "storeMenu")
    private StoreOrderMenu storeOrderMenu;

}
