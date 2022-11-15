package of_f.of_f_spring.dto.platform;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class PlatformInfoDTO {
    private Long seq;
    private Long platformSeq;
    private String name;
    private String value;
}
