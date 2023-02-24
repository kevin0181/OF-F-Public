package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreOrderRepository extends JpaRepository<StoreOrder, Long> {
    StoreOrder findById(String merchantUid);
    List<StoreOrder> findAllByStoreSeqAndStatusOrStatusAndPayStatusOrderByDateDesc(Long id, int status, int status2, int payStatus);
}
