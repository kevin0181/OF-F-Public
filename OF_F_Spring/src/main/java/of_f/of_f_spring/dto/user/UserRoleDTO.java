package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.user.Role;

@Getter
@Setter
public class UserRoleDTO {
    private Long seq;
    private Long userSeq;
    private Long roleSeq;
    private Role roles;
}
