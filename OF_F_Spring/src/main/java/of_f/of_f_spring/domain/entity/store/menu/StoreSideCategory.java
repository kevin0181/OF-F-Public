package of_f.of_f_spring.domain.entity.store.menu;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.Store;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Side_Category")
public class StoreSideCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_seq")
    private Long storeSeq;

    @Column
    private String name;

    @Column
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Store_Side_Category_seq")
    private List<StoreMS> storeMSs;

    @OneToMany
    @JoinColumn(name = "Store_Side_Category_seq")
    private List<StoreSideMenu> storeSideMenus;

    @ManyToOne
    @JoinColumn(name = "Store_seq", insertable = false, updatable = false)
    private Store store;

}
