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
    FAIL_LOGIN(HttpServletResponse.SC_UNAUTHORIZED, "A0003", "fail login", "로그인 실패", "아이디 및 비밀번호가 일치하지 않습니다."),
    NOT_EXIT_EMAIL_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "A0004", "not found email token", "이메일 토큰 확인 불가", "이메일 인증에 실패하였습니다."),
    NOT_EXIT_USER(HttpServletResponse.SC_UNAUTHORIZED, "A0005", "does not exit user", "존재하지 않는 사용자", "존재하지 않는 사용자입니다."),
    BLOCK_USER(HttpServletResponse.SC_UNAUTHORIZED, "A0006", "black user", "잠긴 사용자", "사용자의 계정을 사용할 수 없습니다. 관리자에게 문의 주세요."),
    // --token--
    INVALID_TOKEN_INFO(HttpServletResponse.SC_NOT_FOUND, "T0001", "invalid token", "토큰이 유효하지 않습니다."),
    TIMEOUT_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "T0002", "expiration date", "토큰 유효기간 만료"),
    TOKEN_DOES_NOT_EXIT(HttpServletResponse.SC_NOT_FOUND, "T0003", "does not exist Token", "토큰이 존재하지 않습니다"),
    NOT_MATCH_TOKEN(HttpServletResponse.SC_BAD_REQUEST, "T0004", "not match token", "토큰이 일치하지 않습니다.");

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
