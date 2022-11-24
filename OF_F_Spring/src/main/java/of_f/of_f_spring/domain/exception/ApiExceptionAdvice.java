package of_f.of_f_spring.domain.exception;

import of_f.of_f_spring.domain.exception.dto.ApiExceptionDTO;
import of_f.of_f_spring.domain.exception.dto.AuthExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionDTO> apiExceptionHandler(HttpServletRequest request, final ApiException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionDTO.builder()
                        .errorCode(e.getError().getCode())
                        .error(e.getError().getError())
                        .errorMessage(e.getError().getMessage())
                        .build());
    }

    @ExceptionHandler({AuthException.class})
    public ResponseEntity<AuthExceptionDTO> authExceptionHandler(HttpServletRequest request, final AuthException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(AuthExceptionDTO.builder()
                        .errorCode(e.getError().getCode())
                        .error(e.getError().getError())
                        .errorMessage(e.getError().getMessage())
                        .detail(e.getError().getDetail())
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
                        .errorCode("400")
                        .error("fail validation")
                        .errorMessage(bindingResult.getFieldError().getDefaultMessage())
                        .build());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ApiExceptionDTO> NullQuery(HttpServletRequest request, MissingServletRequestParameterException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionDTO.builder()
                        .errorCode("400")
                        .error("Bad Request")
                        .errorMessage(e.getMessage())
                        .build());
    }
}
