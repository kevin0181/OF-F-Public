package of_f.of_f_spring.domain.entity.store.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.Store;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "Store_Category")
@NoArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Store_Category_seq")
    private List<StoreMenu> storeMenus;

    public StoreCategory(Long seq, Long storeSeq, String name, boolean status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.name = name;
        this.status = status;
    }
}
