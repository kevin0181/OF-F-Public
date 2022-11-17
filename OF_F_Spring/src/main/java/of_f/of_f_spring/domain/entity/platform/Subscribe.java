package of_f.of_f_spring.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Subscribe")
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column(name = "Platform_seq")
    private Long platformSeq;
    private String name;
    private String price;

    @ManyToOne
    @JoinColumn(name = "Platform_seq", insertable = false, updatable = false)
    private Platform platform;

}
