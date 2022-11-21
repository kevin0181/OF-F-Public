package of_f.of_f_spring.controller.user;

import jdk.nashorn.internal.parser.Token;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import of_f.of_f_spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDTO userLoginDTO) {
        TokenInfo tokenInfo = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return tokenInfo;
    }

    @PostMapping("/refresh-token")
    public TokenInfo refreshToken(@RequestBody TokenInfo tokenInfo) {
        TokenInfo refreshTokenInfo = userService.refreshToken(tokenInfo);
        return refreshTokenInfo;
    }

}
