package of_f.of_f_spring.domain.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

@Getter
@ToString
public enum ExceptionEnum {

    INVALID_TOKEN_INFO(HttpServletResponse.SC_NOT_FOUND, "E0001", "토큰이 유효하지 않습니다."),
    TIMEOUT_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "E0001", "토큰 유효기간 만료");

    private final int status;
    private final String code;
    private String message;

    ExceptionEnum(int status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
