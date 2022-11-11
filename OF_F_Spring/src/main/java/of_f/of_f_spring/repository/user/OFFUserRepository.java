package of_f.of_f_spring.repository.user;

import of_f.of_f_spring.domain.entity.OFFUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OFFUserRepository extends JpaRepository<OFFUser, Long> {

}
