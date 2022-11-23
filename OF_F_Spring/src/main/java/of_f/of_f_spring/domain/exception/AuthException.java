package of_f.of_f_spring.domain.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private ExceptionEnum error;

    public AuthException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}