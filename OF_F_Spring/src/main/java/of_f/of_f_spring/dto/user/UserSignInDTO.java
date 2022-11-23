package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.dto.store.StoreDTO;

import java.util.List;

@Getter
@Setter
public class UserSignInDTO {
    private Long seq;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private int userStatus;
    private boolean emailReceiveStatus;
    private boolean phoneNumberReceiveStatus;
    private List<UserRoleDTO> userRoles;
}
