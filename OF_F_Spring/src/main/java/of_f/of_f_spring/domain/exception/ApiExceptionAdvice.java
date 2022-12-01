package of_f.of_f_spring.domain.exception;

import of_f.of_f_spring.domain.exception.dto.AdminExceptionDTO;
import of_f.of_f_spring.domain.exception.dto.ApiExceptionDTO;
import of_f.of_f_spring.domain.exception.dto.AuthExceptionDTO;
import of_f.of_f_spring.domain.exception.dto.StoreExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionDTO> apiExceptionHandler(HttpServletRequest request, final ApiException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getCode())
                .body(ApiExceptionDTO.builder()
                        .code(e.getError().getCode())
                        .status(e.getError().getStatus())
                        .errorCode(e.getError().getErrorCode())
                        .error(e.getError().getError())
                        .errorMessage(e.getError().getMessage())
                        .detail(e.getError().getDetail())
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }

    @ExceptionHandler({AuthException.class})
    public ResponseEntity<AuthExceptionDTO> authExceptionHandler(HttpServletRequest request, final AuthException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getCode())
                .body(AuthExceptionDTO.builder()
                        .code(e.getError().getCode())
                        .status(e.getError().getStatus())
                        .errorCode(e.getError().getErrorCode())
                        .error(e.getError().getError())
                        .errorMessage(e.getError().getMessage())
                        .detail(e.getError().getDetail())
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }

    @ExceptionHandler({StoreException.class})
    public ResponseEntity<StoreExceptionDTO> storeExceptionHandler(HttpServletRequest request, final StoreException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getCode())
                .body(StoreExceptionDTO.builder()
                        .code(e.getError().getCode())
                        .status(e.getError().getStatus())
                        .errorCode(e.getError().getErrorCode())
                        .error(e.getError().getError())
                        .errorMessage(e.getError().getMessage())
                        .detail(e.getError().getDetail())
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }

    @ExceptionHandler({AdminException.class})
    public ResponseEntity<AdminExceptionDTO> adminExceptionHandler(HttpServletRequest request, final AdminException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getCode())
                .body(AdminExceptionDTO.builder()
                        .code(e.getError().getCode())
                        .status(e.getError().getStatus())
                        .errorCode(e.getError().getErrorCode())
                        .error(e.getError().getError())
                        .errorMessage(e.getError().getMessage())
                        .detail(e.getError().getDetail())
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }

    //validation exception handler
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiExceptionDTO> validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionDTO.builder()
                        .code(HttpServletResponse.SC_BAD_REQUEST)
                        .status("BAD")
                        .errorCode("V001")
                        .error("fail validation")
                        .errorMessage("일치하지 않는 유효성")
                        .detail(bindingResult.getFieldError().getDefaultMessage())
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }

    //validation exception handler2
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ApiExceptionDTO> valid2ExceptionHandler(HttpServletRequest request, BindException e) {
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionDTO.builder()
                        .code(HttpServletResponse.SC_BAD_REQUEST)
                        .status("BAD")
                        .errorCode("V002")
                        .error("fail validation")
                        .errorMessage("일치하지 않는 유효성")
                        .detail(bindingResult.getFieldError().getDefaultMessage())
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }

    //reqeust body가 일치하지 않을 경우
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<StoreExceptionDTO> requestBodyExceptionHandler(HttpServletRequest request, final HttpMessageNotReadableException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(StoreExceptionDTO.builder()
                        .code(HttpServletResponse.SC_NOT_FOUND)
                        .status("BAD")
                        .errorCode("404")
                        .error("NOT MATCH BODY DATA")
                        .errorMessage(e.getMessage())
                        .detail("body 형식의 데이터가 존재하지 않거나 일치하지 않습니다.")
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }


    @ExceptionHandler({MissingServletRequestParameterException.class}) //파라미터나 쿼리에 값이 들어오지 않았을때
    public ResponseEntity<ApiExceptionDTO> NullQuery(HttpServletRequest request, MissingServletRequestParameterException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionDTO.builder()
                        .code(HttpServletResponse.SC_BAD_REQUEST)
                        .status("BAD")
                        .errorCode("400")
                        .error("Bad Request")
                        .errorMessage(e.getMessage())
                        .detail("URI의 파라미터가 일치하지 않습니다. 관리자에게 문의해주세요.")
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }
    @ExceptionHandler(NoHandlerFoundException.class) // 404 not found
    public ResponseEntity<ApiExceptionDTO> notFound(HttpServletRequest request, NoHandlerFoundException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiExceptionDTO.builder()
                        .code(HttpServletResponse.SC_NOT_FOUND)
                        .status("BAD")
                        .errorCode("NT0001")
                        .error("NOT FOUND")
                        .errorMessage(e.getMessage())
                        .detail("찾을 수 없는 URI입니다.")
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }

    @ExceptionHandler(Exception.class) // total exception
    public ResponseEntity<ApiExceptionDTO> totalException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .body(ApiExceptionDTO.builder()
                        .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                        .status("BAD")
                        .errorCode("SR0001")
                        .error("server error")
                        .errorMessage(String.valueOf(e))
                        .detail("서버 오류")
                        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build());
    }
}
