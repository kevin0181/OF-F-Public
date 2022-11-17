package of_f.of_f_spring.domain.entity.user;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.platform.Platform;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "platform_seq")
    private Long platformSeq;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany
    @JoinColumn(name = "Role_seq")
    private List<UserRole> userRoles;

    @ManyToOne
    @JoinColumn(name = "platform_seq", insertable = false, updatable = false)
    private Platform platform;

}
