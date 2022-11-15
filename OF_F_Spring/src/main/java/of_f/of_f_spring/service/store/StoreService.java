package of_f.of_f_spring.service.store;

import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public void requestNewStore(UserDTO userDTO) { // 가맹점 생성 신청



    }

}
