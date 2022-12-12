package of_f.of_f_spring.repository.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRStoreInfoRepository extends JpaRepository<QRStoreInfo, Long> {
    QRStoreInfo findByStoreSeq(Long seq);
}
