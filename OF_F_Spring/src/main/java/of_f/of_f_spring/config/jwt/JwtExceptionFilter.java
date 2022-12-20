package of_f.of_f_spring.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response); // JwtAuthenticationFilter로 이동
        } catch (ApiException e) {
            // JwtAuthenticationFilter에서 예외 발생하면 바로 setErrorResponse 호출
            setErrorResponse(request, response, e);
        }
    }

    public void setErrorResponse(HttpServletRequest req, HttpServletResponse res, ApiException e) throws IOException {

//        switch (e.getError().getErrorCode()) {
//            case "TO0001":
                throw new ApiException(ExceptionEnum.INVALID_TOKEN_INFO);
//            case "TO0002":
//                throw new ApiException(ExceptionEnum.TIMEOUT_TOKEN); //-> 토큰 재발행 요청
//        }

    }
}
