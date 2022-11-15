package of_f.of_f_spring.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String platform;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Platform_seq")
    private List<PlatformInfo> platformInfos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Platform_seq")
    private List<Subscribe> subscribes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Platform_seq")
    private List<Role> roles;

}
