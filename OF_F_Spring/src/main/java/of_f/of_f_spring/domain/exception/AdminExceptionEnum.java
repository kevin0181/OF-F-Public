package of_f.of_f_spring.domain.exception;

import lombok.Getter;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public enum AdminExceptionEnum {

    NO_MATCH_STORE(HttpServletResponse.SC_NOT_FOUND, "BAD", "AD0001", "not match store", "존재하지 않는 가맹점", "존재하지 않는 가맹점 입니다."),
    STORE_STATUS_SAVE_EXCEPTION(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "BAD", "AD0002", "can not save store status", "가맹점 상태 변경 실패", "가맹점 상태 변경에 실패하였습니다."),
    STORE_QR_INFO_FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "BAD", "AD0003", "server error by store qr info", "가맹점 QR 정보 변경 실패", "오류로 인해 가맹점의 QR 정보를 변경하지 못했습니다."),
    ALREADY_STORE_QR_INFO(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AD0004", "already store qr info", "이미 존재하는 qr 정보", "이미 qr 정보가 존재합니다.");

    private final int code;
    private final String status;
    private final String errorCode;
    private String error;
    private String message;
    private String detail;
    private String localDateTime;

    AdminExceptionEnum(int code, String status, String errorCode, String error, String message, String detail) {
        this.code = code;
        this.status = status;
        this.errorCode = errorCode;
        this.error = error;
        this.message = message;
        this.detail = detail;
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
