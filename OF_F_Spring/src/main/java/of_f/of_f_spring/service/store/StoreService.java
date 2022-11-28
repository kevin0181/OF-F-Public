package of_f.of_f_spring.service.store;

import lombok.extern.slf4j.Slf4j;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.StoreException;
import of_f.of_f_spring.domain.exception.StoreExceptionEnum;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.repository.store.StoreRepository;
import of_f.of_f_spring.repository.user.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    public ApiResponseDTO applicationNewStore(StoreDTO storeDTO, Principal principal) {  // 가맹점 신청

        if (principal == null || principal.getName() == null)
            throw new StoreException(StoreExceptionEnum.DOES_NO_EXIT_USER); //존재하지 않는 회원

        User user = userRepository.findByEmail(principal.getName());

        if (user == null)
            throw new StoreException(StoreExceptionEnum.DOES_NO_EXIT_USER); //존재하지 않는 회원

        if (user.getStore().size() != 0)
            throw new StoreException(StoreExceptionEnum.ALREADY_EXIST_STORE); //이미 존재하는 가게

        try {

            storeDTO.setStatus(5);
            storeDTO.setUserSeq(user.getSeq());

            Store store = StoreMapper.instance.storeDTOTOStore(storeDTO);

            storeRepository.save(store);

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            log.error(String.valueOf(e));
            throw new StoreException(StoreExceptionEnum.SAME_BUSINESS_NUMBER);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new StoreException(StoreExceptionEnum.REQ_NEW_STORE_ERROR);
        }

        return ApiResponseDTO.builder()
                .message("가맹점 신청 성공")
                .detail("가맹점 신청이 완료되었습니다. 7일 이내 번호 연락 예정")
                .build();
    }
}
