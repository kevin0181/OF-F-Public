package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.menu.StoreMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {
}
