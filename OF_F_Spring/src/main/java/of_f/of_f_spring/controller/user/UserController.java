package of_f.of_f_spring.controller.user;

import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.dto.user.*;
import of_f.of_f_spring.service.user.EmailService;
import of_f.of_f_spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/n/login") // 로그인
    public TokenInfo login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }

    @PostMapping("/n/signIn") // 회원가입
    public ResUserDTO signIn(@RequestBody @Valid UserSignInDTO userSignInDTO) {
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
    @PostMapping("/y/refresh-token") // 토큰 재발행
    public TokenInfo refreshToken(@RequestHeader(required = false) String Authorization,
                                  @RequestBody @Valid TokenInfo tokenInfo) {
        return userService.refreshTokenService(Authorization, tokenInfo);
    }

    @PostMapping("/y/logout") // 로그아웃
    public boolean logout(Principal principal) {
        return userService.deleteRefreshToken(principal);
    }

    @GetMapping("/n/email/check") // 이메일 인증
    public boolean checkEmail(@RequestParam String email) throws MessagingException {
        return emailService.saveEmailToken(email);
    }

    @GetMapping("/n/email/check/token") // 이메일 토큰 인증
    public VerifyEmailInfo checkEmailToken(@RequestParam(required = false) String emailToken) {
        return emailService.checkToken(emailToken);
    }

}
