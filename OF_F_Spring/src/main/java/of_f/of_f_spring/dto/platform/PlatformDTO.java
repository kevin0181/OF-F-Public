package of_f.of_f_spring.dto.platform;

import lombok.Getter;
import lombok.Setter;
import of_f.of_f_spring.dto.user.RoleDTO;

import java.util.List;

@Getter
@Setter
public class PlatformDTO {
    private Long seq;
    private String platform;
    private List<PlatformInfoDTO> platformInfos;
    private List<SubscribeDTO> subscribes;
    private List<RoleDTO> roles;
}
