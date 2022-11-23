package of_f.of_f_spring.domain.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiExceptionDTO {
    private String errorCode;
    private String error;
    private String errorMessage;
}
