package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.user.User;
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
        List<User> users = userService.getUserList();
        if (users == null)
            throw new NullPointerException();

        if (users.size() == 0)
            throw new Exception("회원 리스트에 회원이 존재하지 않습니다.");

        System.out.println("회원의 데이터를 정상적으로 가져왔습니다.");
    }

}
