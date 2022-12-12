package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.menu.StoreCategory;
import of_f.of_f_spring.domain.entity.store.menu.StoreSideCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreSideCategoryRepository extends JpaRepository<StoreSideCategory, Long> {
}
