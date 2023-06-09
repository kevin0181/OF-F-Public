package of_f.of_f_spring.domain.entity.store.menu;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Store_Side_Img")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreSideImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Store_Side_Menu_seq")
    private Long storeSideMenuSeq;

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private String extension;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "Store_Side_Menu_seq", insertable = false, updatable = false)
    private StoreSideMenu storeSideMenu;

}
