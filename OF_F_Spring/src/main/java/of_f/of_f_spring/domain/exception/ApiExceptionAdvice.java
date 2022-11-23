package of_f.of_f_spring.domain.exception;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getError().getCode())
                        .errorMessage(e.getError().getMessage())
                        .build());
    }

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<ApiExceptionEntity> InvalidTokenHandler(HttpServletRequest request, final MalformedJwtException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiExceptionEntity.builder()
                        .errorCode("123")
                        .errorMessage("토큰 오류임")
                        .build());
    }
}
