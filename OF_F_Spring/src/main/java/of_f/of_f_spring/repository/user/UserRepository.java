package of_f.of_f_spring.repository.user;

import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.dto.user.FindUserEmailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    User findByPhoneNumberAndName(String phoneNumber, String name);
}
