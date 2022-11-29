package of_f.of_f_spring.service.store;

import lombok.extern.slf4j.Slf4j;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.AdminException;
import of_f.of_f_spring.domain.exception.AdminExceptionEnum;
import of_f.of_f_spring.domain.exception.StoreException;
import of_f.of_f_spring.domain.exception.StoreExceptionEnum;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.domain.mapper.user.UserMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.repository.store.StoreRepository;
import of_f.of_f_spring.repository.user.UserRepository;
import of_f.of_f_spring.service.user.EmailService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Slf4j
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public ApiResponseDTO applicationNewStore(StoreDTO storeDTO, Principal principal) {  // 가맹점 신청

        if (principal == null || principal.getName() == null)
            throw new StoreException(StoreExceptionEnum.DOES_NO_EXIT_USER); //존재하지 않는 회원

        User user = userRepository.findByEmail(principal.getName());

        if (user == null)
            throw new StoreException(StoreExceptionEnum.DOES_NO_EXIT_USER); //존재하지 않는 회원

        if (user.getStores().size() != 0)
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


    @Transactional(rollbackFor = AdminException.class)
    public ApiResponseDTO responseApplicationStore(Long storeId, int status) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null)
            throw new AdminException(AdminExceptionEnum.NO_MATCH_STORE);

        store.setStatus(status);

        if (storeRepository.save(store) != null)
            return emailService.StoreStatusResEmail(status, store.getUser());
        else
            throw new AdminException(AdminExceptionEnum.STORE_STATUS_SAVE_EXCEPTION);

    }

    public ApiResponseDTO getStoreInfoAdmin(Principal principal) { //가맹점 정보

        User user = userRepository.findByEmail(principal.getName());

        if (user.getStores() == null || user.getStores().size() == 0)
            throw new StoreException(StoreExceptionEnum.NONEXISTENT_STORE); //존재 하지 않는 가게

        UserDTO userDTO = UserMapper.instance.userTOUserDTO_N_ROLE(user);


        if (userDTO.getStores() != null && userDTO.getStores().size() != 1)
            throw new StoreException(StoreExceptionEnum.ALREADY_EXIST_STORE); // 여러개의 가게를 가지고 있을 경우 -> 추후 오픈 예정

        for (int i = 0; i < userDTO.getStores().size(); i++) { //여러개의 가맹점 중, 활성화가 되어있지 않은 가맹점이면 제외하고 정보를 리턴
            if (userDTO.getStores().get(i).getStatus() != 0) {
                userDTO.getStores().remove(i);
            }
        }

        return ApiResponseDTO.builder()
                .message("가맹점 정보")
                .detail("가맹점의 정보 입니다.")
                .data(userDTO)
                .build();

    }
}
