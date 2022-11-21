package of_f.of_f_spring.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.user.Role;

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
