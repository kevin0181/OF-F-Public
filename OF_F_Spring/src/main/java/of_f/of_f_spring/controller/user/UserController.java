package of_f.of_f_spring.controller.user;

import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.AuthException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.dto.ResponseDTO;
import of_f.of_f_spring.dto.user.*;
import of_f.of_f_spring.service.user.EmailService;
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

    @Autowired
    private EmailService emailService;

    @PostMapping("/n/login") // 로그인
    public TokenInfo login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }

    @PostMapping("/n/signIn") // 회원가입
    public ResUserDTO signIn(@RequestBody @Valid UserSignInDTO userSignInDTO, @RequestParam(required = false) String emailToken) {
        return userService.defaultSaveUser(userSignInDTO, emailToken);
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

    /*
    error -> 400 email 없음
     */
    @GetMapping("/n/email/check") // 이메일 인증
    public boolean checkEmail(@RequestBody @Valid EmailToken emailToken) {
        return emailService.saveEmailToken(emailToken);
    }

    @PostMapping("/y/check/user") //사용자 비밀번호 확인
    public boolean checkUser(@RequestBody UserLoginDTO userLoginDTO, Principal principal) {
        return userService.checkUser(userLoginDTO, principal);
    }

    /*
    무조건 3개 정보 전부다 보내줘야함.
     */
    @PostMapping("/y/change/info") //사용자 정보 변경
    public boolean changeUserInfo(@RequestBody @Valid ChangeUserDTO changeUserDTO, Principal principal) {
        User user = userService.changeUser(changeUserDTO, principal);
        if (user == null)
            throw new AuthException(ExceptionEnum.CAN_NOT_CHANGE_USER_INFO);
        return true;
    }

    @PostMapping("/n/find/email") //이메일 찾기
    public String findEmail(@RequestBody @Valid FindUserEmailDTO findUserEmailDTO) {
        return userService.findEmail(findUserEmailDTO);
    }

    @PostMapping("/n/find/password") //비밀번호 찾기
    public boolean findPasswordSendEmail(@RequestBody @Valid FindUserPasswordDTO findUserPasswordDTO) {
        return emailService.sendPasswordEmail(findUserPasswordDTO);
    }

    @GetMapping("/n/find/password/check/token") //비밀번호 찾기 -> 변경 & 토큰 체크
    public ResponseDTO checkChangePasswordEmailToken(@RequestParam(required = false) String emailToken, @RequestBody UserLoginDTO userLoginDTO) {
        return emailService.checkFindPasswordToken(emailToken, userLoginDTO);
    }


}
