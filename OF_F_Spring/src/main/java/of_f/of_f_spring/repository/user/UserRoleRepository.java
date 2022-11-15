package of_f.of_f_spring.repository.user;

import of_f.of_f_spring.domain.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
