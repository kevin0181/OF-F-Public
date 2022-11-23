package of_f.of_f_spring.domain.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiExceptionEntity {
    private String errorCode;
    private String errorMessage;
}
