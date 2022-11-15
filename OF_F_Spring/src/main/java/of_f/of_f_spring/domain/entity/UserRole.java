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

    @Column(name = "User_seq")
    private Long userSeq;

    @Column(name = "Role_seq")
    private Long roleSeq;

    @ManyToOne
    @JoinColumn(name = "Role_seq", insertable = false, updatable = false)
    private Role roles;
}
