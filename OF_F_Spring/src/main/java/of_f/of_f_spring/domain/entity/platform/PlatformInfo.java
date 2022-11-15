package of_f.of_f_spring.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Platform_Info")
public class PlatformInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "Platform_seq")
    private Long platformSeq;

    @Column
    private String name;

    @Column
    private String value;

}
