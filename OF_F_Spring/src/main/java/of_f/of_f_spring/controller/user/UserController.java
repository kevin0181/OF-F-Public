package of_f.of_f_spring.controller.user;

import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.dto.user.ResUserDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import of_f.of_f_spring.dto.user.UserSignInDTO;
import of_f.of_f_spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }

    @PostMapping("/signIn")
    public ResUserDTO signIn(@RequestBody UserSignInDTO userSignInDTO) {
        return userService.defaultSaveUser(userSignInDTO);
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
    public TokenInfo refreshToken(@RequestHeader(required = false) String Authorization, @RequestBody @Valid TokenInfo tokenInfo) {
        return userService.refreshTokenService(Authorization, tokenInfo);
    }

    @PostMapping("/logout")
    public boolean logout(Principal principal) {
        return userService.deleteRefreshToken(principal);
    }

}
