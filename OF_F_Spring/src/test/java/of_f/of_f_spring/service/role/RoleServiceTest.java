package of_f.of_f_spring.service.role;

import of_f.of_f_spring.dto.user.RoleDTO;
import of_f.of_f_spring.service.user.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void saveRole() throws Exception {

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setRoleName("QR_Tech");

        roleService.createRole(roleDTO);

    }

    @Test
    public void getRole() throws Exception {
        roleService.getListRole();
    }

}
