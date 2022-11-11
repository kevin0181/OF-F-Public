package of_f.of_f_spring.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "User_Role")
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "OF_F_User_seq")
    private Long OFFUserSeq;

    @Column(name = "Role_seq")
    private Long RoleSeq;

    @ManyToOne
    @JoinColumn(name = "Role_seq", insertable = false, updatable = false)
    private Role roles;
}
