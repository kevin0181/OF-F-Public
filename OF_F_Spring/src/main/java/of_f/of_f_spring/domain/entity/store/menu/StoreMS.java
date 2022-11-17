package of_f.of_f_spring.domain.entity.store.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Store_MS")
public class StoreMS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Menu_seq")
    private Long storeMenuSeq;

    @Column(name = "Store_Side_Category_seq")
    private Long storeSideCategorySeq;

    @ManyToOne
    @JoinColumn(name = "Store_Menu_seq", insertable = false, updatable = false)
    private StoreMenu storeMenu;

    @ManyToOne
    @JoinColumn(name = "Store_Side_Category_seq", insertable = false, updatable = false)
    private StoreSideCategory storeSideCategory;

}
