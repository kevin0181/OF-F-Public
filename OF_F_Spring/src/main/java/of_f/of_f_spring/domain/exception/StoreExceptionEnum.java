package of_f.of_f_spring.domain.exception;

import lombok.Getter;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public enum StoreExceptionEnum {

    DOES_NO_EXIT_USER(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "S0001", "not match user", "존재하지 않는 회원", "로그인 후 이용이 가능합니다."),
    REQ_NEW_STORE_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "BAD", "S0002", "server error", "서버 오류", "가맹점 신청 오류 (관리자에게 문의 주세요.)"),
    SAME_BUSINESS_NUMBER(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "S0003", "same business number", "동일한 사업자 번호", "이미 등록된 사업자 번호 입니다. (관리자에게 문의 주세요)"),
    ALREADY_EXIST_STORE(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "S0004", "already exist store", "이미 존재하는 가게", "계정당 한개의 가맹점을 가질 수 있습니다."),
    NONEXISTENT_STORE(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "S0005", "does not exist store", "존재하지 않는 가게", "가맹점의 정보가 존재하지 않습니다. 가맹점 신청을 해주세요."),
    FAIL_SAVE_CATEGORY(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "BAD", "S0006", "can not save category", "카테고리를 저장 실패", "카테고리를 저장할 수 없습니다."),
    NONEXISTENT_STORE_CATEGORY(HttpServletResponse.SC_NOT_FOUND, "BAD", "S0007", "does not exist category", "존재하지 않는 카테고리", "카테고리가 존재하지 않습니다."),
    FAIL_UPDATE_CATEGORY(HttpServletResponse.SC_UNAUTHORIZED, "BAD", "S0008", "can not update category", "카테고리 수정 실패", "카테고리를 수정할 수 없습니다.");
    private final int code;
    private final String status;
    private final String errorCode;
    private String error;
    private String message;
    private String detail;
    private String localDateTime;

    StoreExceptionEnum(int code, String status, String errorCode, String error, String message, String detail) {
        this.code = code;
        this.status = status;
        this.errorCode = errorCode;
        this.error = error;
        this.message = message;
        this.detail = detail;
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
