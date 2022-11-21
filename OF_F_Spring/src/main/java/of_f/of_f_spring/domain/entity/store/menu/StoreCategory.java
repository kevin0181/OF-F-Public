package of_f.of_f_spring.domain.entity.store.menu;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.Store;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Category")
public class StoreCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_seq")
    private Long storeSeq;

    @Column
    private String name;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "Store_seq", insertable = false, updatable = false)
    private Store store;

    @OneToMany
    @JoinColumn(name = "Store_Category_seq")
    private List<StoreMenu> storeMenus;


}
