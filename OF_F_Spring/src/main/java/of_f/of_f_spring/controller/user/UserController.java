package of_f.of_f_spring.controller.user;

import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import of_f.of_f_spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDTO userLoginDTO) {
        TokenInfo tokenInfo = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return tokenInfo;
    }

    /*
    Header{
        Authorization : Bearer refreshToken
    },
    body{
        tokenInfo
    }
     */
    @PostMapping("/refresh-token")
    public TokenInfo refreshToken(@RequestBody TokenInfo tokenInfo) {
        TokenInfo refreshTokenInfo = userService.refreshTokenService(tokenInfo);
        return refreshTokenInfo;
    }

    @PostMapping("/logout")
    public boolean logout(Principal principal) {
        boolean result = userService.deleteRefreshToken(principal);
        return result;
    }

}
