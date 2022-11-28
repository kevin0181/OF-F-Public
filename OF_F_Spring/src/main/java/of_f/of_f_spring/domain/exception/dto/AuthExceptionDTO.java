package of_f.of_f_spring.domain.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthExceptionDTO {
    private int code;
    private String status;
    private String errorCode;
    private String error;
    private String errorMessage;
    private String detail;
    private String timestamp;
}
