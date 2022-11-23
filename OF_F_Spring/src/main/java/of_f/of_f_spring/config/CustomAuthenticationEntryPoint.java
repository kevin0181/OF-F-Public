package of_f.of_f_spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //로그인 쪽 오류 났을때.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final Map<String, Object> body = new HashMap<>();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 응답 객체 초기화
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", authException.getMessage());
        body.put("message", "로그인 실패");
        body.put("detail", "아이디나 비밀번호가 일치하지 않습니다.");
        body.put("path", request.getServletPath());
        body.put("code", "A0003");
        final ObjectMapper mapper = new ObjectMapper();
        // response 객체에 응답 객체를 넣어줌
        mapper.writeValue(response.getOutputStream(), body);
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
