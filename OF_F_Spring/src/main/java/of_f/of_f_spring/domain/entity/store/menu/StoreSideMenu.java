package of_f.of_f_spring.domain.entity.store.menu;

import lombok.*;
import of_f.of_f_spring.domain.entity.store.order.StoreOrderSide;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Side_Menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreSideMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Side_Category_seq")
    private Long storeSideCategorySeq;

    @Column
    private String name;

    @Column
    private String price;

    @Column
    private boolean status;

    @Column(name = "sold_out_status")
    private boolean soldOutStatus;

    @ManyToOne
    @JoinColumn(name = "Store_Side_Category_seq", insertable = false, updatable = false)
    private StoreSideCategory storeSideCategory;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Store_Side_Menu_seq")
    private List<StoreSideImg> storeSideImgs;

    @OneToOne(mappedBy = "storeSideMenu")
    private StoreOrderSide storeOrderSide;

}
