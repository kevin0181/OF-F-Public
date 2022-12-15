package of_f.of_f_spring.domain.exception;

import lombok.Getter;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public enum OrderExceptionEnum {

    DOES_NOT_EXIST_QR_ID(HttpServletResponse.SC_NOT_FOUND, "BAD", "QR0001", "not found QR ID", "존재하지 않는 QR ID", "존재하지 않는 QR 입니다.");


    private final int code;
    private final String status;
    private final String errorCode;
    private String error;
    private String message;
    private String detail;
    private String localDateTime;

    OrderExceptionEnum(int code, String status, String errorCode, String error, String message, String detail) {
        this.code = code;
        this.status = status;
        this.errorCode = errorCode;
        this.error = error;
        this.message = message;
        this.detail = detail;
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
