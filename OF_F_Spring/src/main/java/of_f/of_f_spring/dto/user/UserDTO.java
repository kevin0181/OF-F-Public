package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.dto.store.StoreDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDTO implements UserDetails {
    private Long seq;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private int userStatus;
    private boolean emailReceiveStatus;
    private boolean phoneNumberReceiveStatus;
    private String createTime;
    private List<UserRoleDTO> userRoles;
    private List<StoreDTO> stores;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0; i < userRoles.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(userRoles.get(i).getRoles().getRoleName()));
        }
        return authorities;
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
