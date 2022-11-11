package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.OFFUser;
import of_f.of_f_spring.dto.user.OFFUserDTO;
import of_f.of_f_spring.dto.user.UserRoleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OFFUserServiceTest {

    @Autowired
    OFFUserService offUserService;

    @Test
    public void getUserList() throws Exception { // 회원 리스트 가져오기
        List<OFFUser> OFFUsers = offUserService.getUserList();
        if (OFFUsers == null)
            throw new NullPointerException();

        if (OFFUsers.size() == 0)
            throw new Exception("회원 리스트에 회원이 존재하지 않습니다.");

        System.out.println("회원의 데이터를 정상적으로 가져왔습니다.");
    }

    @Test
    public void saveUser() {
        OFFUserDTO offUserDTO = new OFFUserDTO();
        offUserDTO.setName("유영빈");
        offUserDTO.setEmail("kevin0183@naver.com");
        offUserDTO.setPassword("1");
        offUserDTO.setUserStatus(1);
        offUserDTO.setPhoneNumber("01000000000");
        offUserDTO.setEmailReceiveStatus(true);
        offUserDTO.setPhoneNumberReceiveStatus(true);

        OFFUser offUser = offUserService.defaultSaveUser(offUserDTO);
    }

    @Test
    public void createStore() {

    }


}
