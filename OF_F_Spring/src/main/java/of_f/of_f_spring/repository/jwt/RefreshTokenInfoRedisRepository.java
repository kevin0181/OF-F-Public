package of_f.of_f_spring.repository.jwt;

import of_f.of_f_spring.config.jwt.RefreshTokenInfo;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenInfoRedisRepository extends CrudRepository<RefreshTokenInfo, String> {
}
