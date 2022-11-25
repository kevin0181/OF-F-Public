package of_f.of_f_spring.domain.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

@Getter
@ToString
public enum ExceptionEnum {

    //auth
    CANNOT_LOGOUT(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A0001", "can not logout", "로그아웃 접근 불가", "로그아웃이 불가능합니다."),
    ALREADY_EMAIL(HttpServletResponse.SC_BAD_REQUEST, "A0002", "already email", "중복 이메일", "이미 존재하는 이메일 입니다."),
    ALREADY_NUMBER(HttpServletResponse.SC_BAD_REQUEST, "A0003", "already phoneNumber", "중복 전화번호", "이미 존재하는 전화번호 입니다."),
    FAIL_LOGIN(HttpServletResponse.SC_UNAUTHORIZED, "A0004", "fail login", "로그인 실패", "아이디 및 비밀번호가 일치하지 않습니다."),
    FAIL_PASSWORD(HttpServletResponse.SC_UNAUTHORIZED, "A0005", "fail login", "로그인 실패", "비밀번호가 일치하지 않습니다."),
    NOT_EXIT_EMAIL_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "A0006", "not found email token", "이메일 토큰 확인 불가", "이메일 인증에 실패하였습니다."),
    NOT_EXIT_USER(HttpServletResponse.SC_UNAUTHORIZED, "A0007", "does not exit user", "존재하지 않는 사용자", "존재하지 않는 사용자입니다."),
    BLOCK_USER(HttpServletResponse.SC_UNAUTHORIZED, "A0008", "black user", "잠긴 사용자", "사용자의 계정을 사용할 수 없습니다. 관리자에게 문의 주세요."),
    CAN_NOT_USE_MAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A0009", "can not use mail", "이메일 인증 사용 불가", "일시적으로 이메일 인증을 사용이 불가능합니다. 관리자에게 문의 주세요."),
    CAN_NOT_CHANGE_USER_INFO(HttpServletResponse.SC_UNAUTHORIZED, "A0010", "can not change user info", "사용자의 정보를 변경할 수 없습니다."),
    MISS_MATCH_PASSWORD(HttpServletResponse.SC_UNAUTHORIZED, "A0011", "miss match password", "비밀번호와 비밀번호 확인이 일치하지 않습니다."),

    // --token--
    INVALID_TOKEN_INFO(HttpServletResponse.SC_NOT_FOUND, "T0001","invalid token","토큰이 유효하지 않습니다."),

    TIMEOUT_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "T0002","expiration date","토큰 유효기간 만료"),

    TOKEN_DOES_NOT_EXIT(HttpServletResponse.SC_NOT_FOUND, "T0003","does not exist Token","토큰이 존재하지 않습니다"),

    NOT_MATCH_TOKEN(HttpServletResponse.SC_BAD_REQUEST, "T0004","not match token","토큰이 일치하지 않습니다.");

    private final int status;
    private final String code;
    private String error;
    private String message;
    private String detail;

    ExceptionEnum(int status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(int status, String code, String error, String message) {
        this.status = status;
        this.code = code;
        this.error = error;
        this.message = message;
    }

    ExceptionEnum(int status, String code, String error, String message, String detail) {
        this.status = status;
        this.code = code;
        this.error = error;
        this.message = message;
        this.detail = detail;
    }


}
