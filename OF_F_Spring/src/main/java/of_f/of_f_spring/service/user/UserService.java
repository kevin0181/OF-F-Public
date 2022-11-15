package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.mapper.user.UserMapper;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.dto.user.UserRoleDTO;
import of_f.of_f_spring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getUserList() { // 회원 리스트 가져오기
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = UserMapper.instance.UserTOUserDTO(users);
        return userDTOS;
    }

    public User defaultSaveUser(UserDTO userDTO) { //회원 가입

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleSeq(1L); // 회원가입 시, 기본 권한

        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        userRoleDTOS.add(userRoleDTO);
        userDTO.setUserRoles(userRoleDTOS); //권한 넣기

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword())); //패스워드 암호화

        User user = UserMapper.instance.UserDTOTOOFFUser(userDTO);
        return userRepository.save(user);
    }

}
