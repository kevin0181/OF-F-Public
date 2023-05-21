package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.user.Role;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.entity.user.UserRole;
import of_f.of_f_spring.domain.exception.AuthException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.domain.mapper.user.RoleMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.user.RoleDTO;
import of_f.of_f_spring.repository.user.RoleRepository;
import of_f.of_f_spring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<RoleDTO> getListRole() { // 권한 가져오기
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = RoleMapper.instance.RolesTORoleDTOs(roles);
        return roleDTOS;
    }

    @Transactional
    public ApiResponseDTO updateRole(String email, Long roleId) {
        User user = userRepository.findByEmail(email);

        for (int i = 0; i < user.getUserRoles().size(); i++) {
            if (user.getUserRoles().get(i).getRoleSeq() == roleId) {
                throw new AuthException(ExceptionEnum.ALREADY_ROLE);
            }
        }

        List<UserRole> userRoles = user.getUserRoles();
        userRoles.add(UserRole.builder()
                .userSeq(user.getSeq())
                .roleSeq(roleId)
                .build());

        user.setUserRoles(userRoles);

        return ApiResponseDTO.builder()
                .message("사용자 권한 변경")
                .detail(email + "의 권한을 변경했습니다.")
                .data(true)
                .build();
    }

}
