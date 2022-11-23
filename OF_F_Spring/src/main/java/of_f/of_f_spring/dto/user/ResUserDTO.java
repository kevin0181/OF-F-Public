package of_f.of_f_spring.dto.user;

import lombok.Builder;
import lombok.Getter;
import of_f.of_f_spring.dto.store.StoreDTO;

import java.util.List;

@Getter
@Builder
public class ResUserDTO {
    private Long seq;
    private String email;
    private String name;
    private String phoneNumber;
    private int userStatus;
    private boolean emailReceiveStatus;
    private boolean phoneNumberReceiveStatus;
    private String createTime;
    private List<UserRoleDTO> userRoles;
    private StoreDTO store;
}
