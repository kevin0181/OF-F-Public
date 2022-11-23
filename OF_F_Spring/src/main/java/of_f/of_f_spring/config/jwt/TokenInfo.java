package of_f.of_f_spring.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {
    @NotBlank(message = "존재하지 않는 토큰 타입")
    @NotNull(message = "존재하지 않는 토큰 타입")
    private String grantType;
    @NotBlank(message = "존재하지 않는 토큰")
    @NotNull(message = "존재하지 않는 토큰")
    private String accessToken;
    @NotBlank(message = "존재하지 않는 토큰")
    @NotNull(message = "존재하지 않는 토큰")
    private String refreshToken;
}
