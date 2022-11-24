package of_f.of_f_spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import of_f.of_f_spring.domain.exception.AuthException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //토큰 없어서 오류났을때
    //로그인 쪽 오류 났을때.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final Map<String, Object> body = new HashMap<>();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 응답 객체 초기화
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", authException.getMessage());
        body.put("path", request.getServletPath());


        if (authException instanceof AuthenticationServiceException) {
//            body.put("message", "존재하지 않는 사용자");
//            body.put("detail", "존재하지 않는 사용자입니다.");
            body.put("message", "로그인 실패");
            body.put("detail", "아이디 및 비밀번호가 일치하지 않습니다.");
        } else if (authException instanceof BadCredentialsException) {
            body.put("message", "로그인 실패");
            body.put("detail", "아이디 및 비밀번호가 일치하지 않습니다.");
        } else if (authException instanceof LockedException) {
            body.put("message", "잠긴 사용자");
            body.put("detail", "사용자의 계정을 사용할 수 없습니다. 관리자에게 문의 주세요.");
        } else if (authException instanceof DisabledException) {
            body.put("message", "잠긴 사용자");
            body.put("detail", "사용자의 계정을 사용할 수 없습니다. 관리자에게 문의 주세요.");
        } else if (authException instanceof AccountExpiredException) {
            body.put("message", "잠긴 사용자");
            body.put("detail", "사용자의 계정을 사용할 수 없습니다. 관리자에게 문의 주세요.");
        } else if (authException instanceof CredentialsExpiredException) {
            body.put("message", "잠긴 사용자");
            body.put("detail", "사용자의 계정을 사용할 수 없습니다. 관리자에게 문의 주세요.");
        }

        final ObjectMapper mapper = new ObjectMapper();
        // response 객체에 응답 객체를 넣어줌
        mapper.writeValue(response.getOutputStream(), body);
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
