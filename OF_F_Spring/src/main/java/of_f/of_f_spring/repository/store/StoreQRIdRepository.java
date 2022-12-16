package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreQRIdRepository extends JpaRepository<StoreQRId, Long> {
    StoreQRId findByQrId(String qrId);
}
