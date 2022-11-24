package of_f.of_f_spring.service.user;

import of_f.of_f_spring.config.jwt.JwtTokenProvider;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.AuthException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.domain.mapper.user.UserMapper;
import of_f.of_f_spring.dto.user.*;
import of_f.of_f_spring.repository.jwt.RefreshTokenInfoRedisRepository;
import of_f.of_f_spring.repository.user.EmailTokenRedisRepository;
import of_f.of_f_spring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
//@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RefreshTokenInfoRedisRepository refreshTokenInfoRedisRepository;

    public List<User> getUserList() { // 회원 리스트 가져오기
        List<User> users = userRepository.findAll();
        return users;
    }

    //회원 가입
    public ResUserDTO defaultSaveUser(UserSignInDTO userSignInDTO) {

        boolean checkEmail = userRepository.existsByEmail(userSignInDTO.getEmail());

        if (checkEmail)
            throw new AuthException(ExceptionEnum.ALREADY_EMAIL);

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleSeq(2L); // 회원가입 시, 기본 권한

        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        userRoleDTOS.add(userRoleDTO);
        userSignInDTO.setUserStatus(1); // 유저 상태
        userSignInDTO.setUserRoles(userRoleDTOS); //권한 넣기

        userSignInDTO.setPassword(passwordEncoder.encode(userSignInDTO.getPassword())); //패스워드 암호화

        User user = UserMapper.instance.UserSignInDTOTOUser(userSignInDTO);


        ResUserDTO resUserDTO = UserMapper.instance.userTOResUserDTO(userRepository.save(user));

        return resUserDTO;
    }

    // 로그인
    public TokenInfo login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        jwtTokenProvider.saveToken(tokenInfo, authentication);

        return tokenInfo;
    }


    // user확인
    public boolean checkUser(UserLoginDTO userLoginDTO, Principal principal) {

        User user = userRepository.findByEmail(principal.getName());

        if (user == null)
            throw new AuthException(ExceptionEnum.NOT_EXIT_USER);

        if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()))
            return true;
        else
            throw new AuthException(ExceptionEnum.FAIL_PASSWORD);
    }

    // jwt token 재발급

    public TokenInfo refreshTokenService(String Authorization, TokenInfo tokenInfo) {

        if (Authorization == null)
            throw new ApiException(ExceptionEnum.TOKEN_DOES_NOT_EXIT);


        String headerRefreshToken = "";
        if (StringUtils.hasText(Authorization) && Authorization.startsWith("Bearer")) {
            headerRefreshToken = Authorization.substring(7);
        }

        if (headerRefreshToken.equals(tokenInfo.getRefreshToken())) {
            TokenInfo token = jwtTokenProvider.refreshToken(tokenInfo);
            return token;
        } else {
            throw new ApiException(ExceptionEnum.NOT_MATCH_TOKEN);
        }

    }

    public boolean deleteRefreshToken(Principal principal) {
        try {
            refreshTokenInfoRedisRepository.deleteById(principal.getName());
            return true;
        } catch (ApiException e) {
            throw new ApiException(ExceptionEnum.CANNOT_LOGOUT); // 로그아웃 불가 메시지
        }
    }


    public User changeUser(ChangeUserDTO changeUserDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        user.setPassword(passwordEncoder.encode(changeUserDTO.getPassword()));
        user.setName(changeUserDTO.getName());
        user.setPhoneNumber(changeUserDTO.getPhoneNumber());
        return userRepository.save(user);
    }

    public String findEmail(FindUserEmailDTO findUserEmailDTO) {
        User user = userRepository.findByPhoneNumberAndName(findUserEmailDTO.getPhoneNumber(), findUserEmailDTO.getName());
        if (user == null)
            throw new AuthException(ExceptionEnum.NOT_EXIT_USER);

        StringBuffer email = new StringBuffer();
        // 먼저 @ 의 인덱스를 찾는다 - 인덱스 값: 5
        int idx = user.getEmail().indexOf("@");

        String mailF = user.getEmail().substring(0, idx);
        int starSize = mailF.length() / 2;
        String mailF_A = mailF.substring(0, starSize);

        String mailB = user.getEmail().substring(idx + 1);

        String star = "";
        for (int i = 0; i < starSize; i++) {
            star += "*";
        }

        email.append(mailF_A);
        email.append(star);
        email.append("@");
        email.append(mailB);

        return String.valueOf(email);

    }
}
