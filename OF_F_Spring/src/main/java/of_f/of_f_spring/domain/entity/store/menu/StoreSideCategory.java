package of_f.of_f_spring.domain.entity.store.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Side_Category")
public class StoreSideCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column
    private String name;

    @Column
    private boolean status;

    @OneToMany
    @JoinColumn(name = "Store_Side_Category_seq")
    private List<StoreMS> storeMSs;

    @OneToMany
    @JoinColumn(name = "Store_Side_Category_seq")
    private List<StoreSideMenu> storeSideMenus;

}
