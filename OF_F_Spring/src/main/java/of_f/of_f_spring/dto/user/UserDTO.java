package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
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
    private StoreDTO store;
}
