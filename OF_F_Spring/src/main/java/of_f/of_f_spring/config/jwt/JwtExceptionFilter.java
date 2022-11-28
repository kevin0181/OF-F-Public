package of_f.of_f_spring.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import of_f.of_f_spring.domain.exception.ApiException;
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

        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final Map<String, Object> body = new HashMap<>();
        body.put("code", e.getError().getCode());
        body.put("status", e.getError().getStatus());
        body.put("error", e.getError());
        body.put("message", e.getMessage());
        body.put("detail", e.getError().getDetail());
        body.put("timestamp",e.getError().getLocalDateTime());
        body.put("path", req.getServletPath());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(res.getOutputStream(), body);
        res.setStatus(HttpServletResponse.SC_OK);
    }
}
