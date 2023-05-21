package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.menu.StoreMS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMSRepository extends JpaRepository<StoreMS, Long> {
}
