package of_f.of_f_spring.controller.user;

import lombok.RequiredArgsConstructor;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.user.*;
import of_f.of_f_spring.service.user.EmailService;
import of_f.of_f_spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final EmailService emailService;


    @PostMapping("/n/login") // 로그인
    public ApiResponseDTO login(@RequestBody @Valid UserLoginDTO userLoginDTO, HttpServletResponse response) {
        return userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword(), response);
    }

    @PostMapping("/n/signIn") // 회원가입
    public ApiResponseDTO signIn(@RequestBody @Valid UserSignInDTO userSignInDTO, @RequestParam(required = false) String emailToken) {
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
    public ApiResponseDTO refreshToken(@RequestHeader(required = false) String Authorization,
                                       @RequestBody @Valid TokenInfo tokenInfo, HttpServletRequest request) {
        return userService.refreshTokenService(Authorization, tokenInfo);
    }

    @PostMapping("/y/logout") // 로그아웃
    public ApiResponseDTO logout(Principal principal) {
        return userService.deleteRefreshToken(principal);
    }

    /*
    error -> 400 email 없음
     */
    @GetMapping("/n/email/check") // 이메일 인증
    public ApiResponseDTO checkEmail(@RequestBody @Valid EmailToken emailToken) {
        return emailService.saveEmailToken(emailToken);
    }

    @PostMapping("/y/check/user") //사용자 비밀번호 확인
    public ApiResponseDTO checkUser(@RequestBody UserLoginDTO userLoginDTO, Principal principal) {
        return userService.checkUser(userLoginDTO, principal);
    }

    /*
    무조건 3개 정보 전부다 보내줘야함.
     */
    @PostMapping("/y/change/info") //사용자 정보 변경
    public ApiResponseDTO changeUserInfo(@RequestBody @Valid ChangeUserDTO changeUserDTO, Principal principal) {
        return userService.changeUser(changeUserDTO, principal);
    }

    @PostMapping("/n/find/email") //이메일 찾기
    public ApiResponseDTO findEmail(@RequestBody @Valid FindUserEmailDTO findUserEmailDTO) {
        return userService.findEmail(findUserEmailDTO);
    }

    @PostMapping("/n/find/password") //비밀번호 찾기
    public ApiResponseDTO findPasswordSendEmail(@RequestBody @Valid FindUserPasswordDTO findUserPasswordDTO) {
        return emailService.sendPasswordEmail(findUserPasswordDTO);
    }

    @PostMapping("/n/find/password/check/token") //비밀번호 찾기 -> 변경 & 토큰 체크
    public ApiResponseDTO checkChangePasswordEmailToken(
            @RequestHeader(required = false) String Authorization,
            @RequestParam(required = false) String emailToken,
            @RequestBody @Valid UserLoginDTO userLoginDTO) {
        return emailService.checkFindPasswordToken(Authorization, emailToken, userLoginDTO);
    }

}
