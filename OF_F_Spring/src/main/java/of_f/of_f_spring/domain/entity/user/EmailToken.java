package of_f.of_f_spring.domain.entity.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@Setter
@RedisHash(value = "emailToken", timeToLive = 3000)
public class EmailToken {
    @Id
    private String emailToken;
    private String email;
}
