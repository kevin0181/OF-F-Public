package of_f.of_f_spring.domain.exception;

import lombok.Getter;

@Getter
public class AdminException extends RuntimeException {
    private AdminExceptionEnum error;

    public AdminException(AdminExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
