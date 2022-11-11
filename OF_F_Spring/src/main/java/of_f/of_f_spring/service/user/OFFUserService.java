package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.OFFUser;
import of_f.of_f_spring.domain.mapper.OFFUserMapper;
import of_f.of_f_spring.dto.user.OFFUserDTO;
import of_f.of_f_spring.dto.user.UserRoleDTO;
import of_f.of_f_spring.repository.user.OFFUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class OFFUserService {

    @Autowired
    private OFFUserRepository offUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<OFFUser> getUserList() { // 회원 리스트 가져오기
        List<OFFUser> OFFUsers = offUserRepository.findAll();
        return OFFUsers;
    }

    public OFFUser defaultSaveUser(OFFUserDTO offUserDTO) { //회원 가입

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleSeq(1L); // 회원가입 시, 기본 권한

        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        userRoleDTOS.add(userRoleDTO);
        offUserDTO.setUserRoleDTOS(userRoleDTOS); //권한 넣기

        offUserDTO.setPassword(passwordEncoder.encode(offUserDTO.getPassword())); //패스워드 암호화

        OFFUser offUser = OFFUserMapper.instance.OFFUserDTOTOOFFUser(offUserDTO);
        return offUserRepository.save(offUser);
    }

}
