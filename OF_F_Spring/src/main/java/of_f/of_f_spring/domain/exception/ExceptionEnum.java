package of_f.of_f_spring.domain.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public enum ExceptionEnum {

    //auth
    CANNOT_LOGOUT(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "BAD", "AU0001", "can not logout", "로그아웃 접근 불가", "로그아웃이 불가능합니다."),
    ALREADY_EMAIL(HttpServletResponse.SC_BAD_REQUEST, "BAD", "AU0002", "already email", "중복 이메일", "이미 존재하는 이메일 입니다."),
    ALREADY_NUMBER(HttpServletResponse.SC_BAD_REQUEST, "BAD", "AU0003", "already phoneNumber", "중복 전화번호", "이미 존재하는 전화번호 입니다."),
    FAIL_LOGIN(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AU0004", "fail login", "로그인 실패", "아이디 및 비밀번호가 일치하지 않습니다."),
    FAIL_PASSWORD(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AU0005", "fail login", "로그인 실패", "비밀번호가 일치하지 않습니다."),
    NOT_EXIT_EMAIL_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AU0006", "not found email token", "이메일 토큰 확인 불가", "이메일 인증에 실패하였습니다."),
    NOT_EXIT_USER(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AU0007", "does not exit user", "존재하지 않는 사용자", "존재하지 않는 사용자입니다."),
    BLOCK_USER(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AU0008", "black user", "잠긴 사용자", "사용자의 계정을 사용할 수 없습니다. 관리자에게 문의 주세요."),
    CAN_NOT_USE_MAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "BAD", "AU0009", "can not use mail", "이메일 인증 사용 불가", "일시적으로 이메일 인증을 사용이 불가능합니다. 관리자에게 문의 주세요."),
    CAN_NOT_CHANGE_USER_INFO(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AU0010", "can not change user info", "사용자 정보 변경 실패", "사용자의 정보를 변경할 수 없습니다."),
    MISS_MATCH_PASSWORD(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AU0011", "miss match password", "비밀번호 및 비밀번호 확인 불일치", "비밀번호와 비밀번호 확인이 일치하지 않습니다."),

    // --token--
    INVALID_TOKEN_INFO(HttpServletResponse.SC_NOT_FOUND, "BAD", "TO0001", "invalid token", "유효하지 않는 토큰", "토큰이 유효하지 않습니다."),

    TIMEOUT_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "TO0002", "expiration date", "토큰 유효기간 만료", "토큰의 유효기간이 만료되었습니다."),

    TOKEN_DOES_NOT_EXIT(HttpServletResponse.SC_NOT_FOUND, "BAD", "TO0003", "does not exist Token", "존재하지 않는 토큰", "토큰이 존재하지 않습니다"),

    NOT_MATCH_TOKEN(HttpServletResponse.SC_BAD_REQUEST, "BAD", "TO0004", "not match token", "일치하지 않는 토큰", "토큰이 일치하지 않습니다."),


    //api
    INVALID_PARAMS(HttpServletResponse.SC_BAD_REQUEST, "BAD", "AP0001", "invalid request", "옳지 않은 요청", "옳지 않은 요청 파라미터 입니다."),
    AUTHORIZATION_FAILED_REQUEST(HttpServletResponse.SC_FORBIDDEN, "BAD", "AP0002", "Authorization failed request", "승인되지 않은 요청", "옳지 않은 접근 입니다."),
    NOT_IMG(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "AP0003", "not img", "옳지 않은 이미지", "이미지는 png, jpg만 가능합니다.");

    private final int code;
    private final String status;
    private final String errorCode;
    private String error;
    private String message;
    private String detail;
    private String localDateTime;

    ExceptionEnum(int code, String status, String errorCode, String error, String message, String detail) {
        this.code = code;
        this.status = status;
        this.errorCode = errorCode;
        this.error = error;
        this.message = message;
        this.detail = detail;
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }


}
