package of_f.of_f_spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDTO {
    private String code;
    private String status;
    private String message;
    private String detail;
}
