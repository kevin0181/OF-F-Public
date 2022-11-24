package of_f.of_f_spring.repository.user;

import of_f.of_f_spring.domain.entity.user.EmailToken;
import org.springframework.data.repository.CrudRepository;

public interface EmailTokenRedisRepository extends CrudRepository<EmailToken, String> {
}
