package of_f.of_f_spring.dto.platform;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeDTO {
    private Long seq;
    private Long platformSeq;
    private String name;
    private String price;
}
