package of_f.of_f_spring.domain.entity.user;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Getter
@Setter
public class User {
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
    @JoinColumn(name = "User_seq")
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "user")
    private Store store;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_seq")
    private List<StoreOrder> storeOrders;

}
