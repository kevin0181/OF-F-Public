package of_f.of_f_spring.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "OF_F_User")
@Getter
@Setter
public class OFFUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_status")
    private int userStatus;

    @Column(name = "email_receive_status")
    private boolean emailReceiveStatus;

    @Column(name = "phone_number_receive_status")
    private boolean phoneNumberReceiveStatus;

    @Column(name = "create_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String createTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OF_F_User_seq")
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "offUser")
    private Store store;

}
