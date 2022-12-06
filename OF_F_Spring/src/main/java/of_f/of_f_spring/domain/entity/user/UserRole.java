package of_f.of_f_spring.domain.entity.user;

import lombok.*;

import javax.persistence.*;

@Entity(name = "User_Role")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "User_seq")
    private Long userSeq;

    @Column(name = "Role_seq")
    private Long roleSeq;

    @ManyToOne
    @JoinColumn(name = "User_seq", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Role_seq", insertable = false, updatable = false)
    private Role roles;
}
