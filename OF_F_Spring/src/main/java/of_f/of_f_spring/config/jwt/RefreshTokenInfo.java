package of_f.of_f_spring.config.jwt;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@RedisHash(value = "refreshTokenList", timeToLive = 604800)
@Builder
public class RefreshTokenInfo {
    @Id
    private String email;
    private String refreshToken;
}
