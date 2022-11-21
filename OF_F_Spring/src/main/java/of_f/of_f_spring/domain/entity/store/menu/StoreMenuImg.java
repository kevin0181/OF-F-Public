package of_f.of_f_spring.domain.entity.store.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Store_Menu_Img")
public class StoreMenuImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Menu_seq")
    private Long storeMenuSeq;

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private String extension;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "Store_Menu_seq", insertable = false, updatable = false)
    private StoreMenu storeMenu;

}
