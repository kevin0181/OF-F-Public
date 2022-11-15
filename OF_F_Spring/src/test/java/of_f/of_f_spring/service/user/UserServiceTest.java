package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.User;
import of_f.of_f_spring.dto.user.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void getUserList() throws Exception { // 회원 리스트 가져오기
        List<User> Users = userService.getUserList();
        if (Users == null)
            throw new NullPointerException();

        if (Users.size() == 0)
            throw new Exception("회원 리스트에 회원이 존재하지 않습니다.");

        System.out.println("회원의 데이터를 정상적으로 가져왔습니다.");
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
