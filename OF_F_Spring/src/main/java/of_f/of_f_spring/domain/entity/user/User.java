package of_f.of_f_spring.domain.entity.user;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity(name = "User")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_seq")
    private List<Store> stores;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0; i < userRoles.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(userRoles.get(i).getRoles().getRoleName()));
        }
        return authorities;
//        List<Role> roles = new ArrayList<>();
//
//        for (UserRole userRole : userRoles) {
//            roles.add(userRole.getRoles());
//        }
//
//        for (int i = 0; i < roles.size(); i++) {
//            authorities.add(new SimpleGrantedAuthority(roles.get(i).getRoleName()));
//        }
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정 잠김 여부
        if (this.userStatus == 8) { //계정 정지
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 비활성화 여부
        if (this.userStatus == 1) { //비활성화
            return false;
        }
        return true;
    }


}
