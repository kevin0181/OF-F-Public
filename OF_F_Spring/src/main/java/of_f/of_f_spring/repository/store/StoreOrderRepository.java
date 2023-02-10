package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOrderRepository extends JpaRepository<StoreOrder, Long> {
    StoreOrder findById(String merchantUid);
}
