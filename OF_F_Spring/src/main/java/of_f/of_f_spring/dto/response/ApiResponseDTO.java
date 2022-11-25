package of_f.of_f_spring.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponseDTO {
    private final int code = 200;
    private final String status = "GOOD";
    private String message;
    private String detail;
    private Object data;
}
