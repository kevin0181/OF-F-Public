package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OFFUserDTO {
    private Long seq;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Integer userStatus;
    private Boolean emailReceiveStatus;
    private Boolean phoneNumberReceiveStatus;
    private String createTime;
    private List<UserRoleDTO> userRoleDTOS;
}
