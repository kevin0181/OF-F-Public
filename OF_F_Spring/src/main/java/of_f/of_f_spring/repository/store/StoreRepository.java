package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByNameContaining(String name);
}
