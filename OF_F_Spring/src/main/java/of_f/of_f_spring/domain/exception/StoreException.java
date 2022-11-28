package of_f.of_f_spring.domain.exception;

import lombok.Getter;

@Getter
public class StoreException extends RuntimeException {
    private StoreExceptionEnum error;

    public StoreException(StoreExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
