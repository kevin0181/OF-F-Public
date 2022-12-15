package of_f.of_f_spring.domain.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {
    private OrderExceptionEnum error;

    public OrderException(OrderExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
