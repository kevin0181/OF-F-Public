package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.domain.entity.Role;

import java.util.List;

@Getter
@Setter
public class PlatformDTO {
    private Long seq;
    private String platform;
    private List<PlatformInfoDTO> platformInfoDTOs;
    private List<SubscribeDTO> subscribeDTOs;
    private List<RoleDTO> roleDTOs;
}
