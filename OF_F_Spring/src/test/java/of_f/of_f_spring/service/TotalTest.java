package of_f.of_f_spring.service;

import of_f.of_f_spring.domain.entity.User;
import of_f.of_f_spring.dto.user.RoleDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.service.role.RoleServiceTest;
import of_f.of_f_spring.service.user.RoleService;
import of_f.of_f_spring.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TotalTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Test
    public void saveRole() throws Exception {

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setRoleName("QR_Tech");

        roleService.createRole(roleDTO);

    }

    @Test
    public void saveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("유영빈");
        userDTO.setEmail("kevin0183@naver.com");
        userDTO.setPassword("1");
        userDTO.setUserStatus(1);
        userDTO.setPhoneNumber("01000000000");
        userDTO.setEmailReceiveStatus(true);
        userDTO.setPhoneNumberReceiveStatus(true);

        User user = userService.defaultSaveUser(userDTO);
    }
}
