package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
