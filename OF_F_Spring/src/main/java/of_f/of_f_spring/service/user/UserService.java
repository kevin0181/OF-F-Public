package of_f.of_f_spring.service.user;

import of_f.of_f_spring.config.jwt.JwtTokenProvider;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.AuthException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.domain.mapper.user.UserMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Autowired
    private EmailTokenRedisRepository emailTokenRedisRepository;

    public List<User> getUserList() { // 회원 리스트 가져오기
        List<User> users = userRepository.findAll();
        return users;
    }

    //회원 가입
    public ApiResponseDTO defaultSaveUser(UserSignInDTO userSignInDTO, String emailToken) {

        if (emailToken == null || emailToken.equals("")) { // email 토큰이 존재하지 않을때 오류
            throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN);
        }

        try {
            EmailToken checkEmailToken = emailTokenRedisRepository.findById(emailToken).get();
            if (checkEmailToken == null)
                throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN); //존재하지 않는 토큰 -> 토큰값 오류 (time out 일수도 있으니 다시 이메일 인증하라는 메시지 보내기)

        } catch (
                NoSuchElementException e) {
            throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN);
        }

        boolean checkEmail = userRepository.existsByEmail(userSignInDTO.getEmail());

        if (checkEmail)
            throw new AuthException(ExceptionEnum.ALREADY_EMAIL); //이미 존재하는 이메일 일때 오류

        if (!userSignInDTO.getPassword().equals(userSignInDTO.getRePassword()))
            throw new AuthException(ExceptionEnum.MISS_MATCH_PASSWORD);

        emailTokenRedisRepository.deleteById(emailToken); // -> 유효성 검사후 토큰 삭제

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleSeq(2L); // 회원가입 시, 기본 권한

        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        userRoleDTOS.add(userRoleDTO);
        userSignInDTO.setUserStatus(0); // 유저 상태
        userSignInDTO.setUserRoles(userRoleDTOS); //권한 넣기

        userSignInDTO.setPassword(passwordEncoder.encode(userSignInDTO.getPassword())); //패스워드 암호화

        User user = UserMapper.instance.UserSignInDTOTOUser(userSignInDTO);


        ResUserDTO resUserDTO = UserMapper.instance.userTOResUserDTO(userRepository.save(user));

        return ApiResponseDTO.builder()
                .message("회원가입 성공")
                .detail("회원가입이 완료되었습니다.")
                .data(resUserDTO)
                .build();
    }

    // 로그인
    public ApiResponseDTO login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        jwtTokenProvider.saveToken(tokenInfo, authentication);

        return ApiResponseDTO.builder()
                .message("로그인 성공")
                .detail("로그인을 성공하였습니다.")
                .data(tokenInfo)
                .build();
    }


    // user확인
    public ApiResponseDTO checkUser(UserLoginDTO userLoginDTO, Principal principal) {

        User user = userRepository.findByEmail(principal.getName());

        if (user == null)
            throw new AuthException(ExceptionEnum.NOT_EXIT_USER);

        if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()))
            return ApiResponseDTO.builder()
                    .message("사용자 정보 확인")
                    .detail("사용자 정보 인증이 완료 되었습니다.")
                    .build();
        else
            throw new AuthException(ExceptionEnum.FAIL_PASSWORD);
    }

    // jwt token 재발급

    public ApiResponseDTO refreshTokenService(String Authorization, TokenInfo tokenInfo) {

        if (Authorization == null)
            throw new ApiException(ExceptionEnum.TOKEN_DOES_NOT_EXIT);


        String headerRefreshToken = "";
        if (StringUtils.hasText(Authorization) && Authorization.startsWith("Bearer")) {
            headerRefreshToken = Authorization.substring(7);
        }

        if (headerRefreshToken.equals(tokenInfo.getRefreshToken())) {
            TokenInfo token = jwtTokenProvider.refreshToken(tokenInfo);
            return ApiResponseDTO.builder()
                    .message("토큰 재발행")
                    .detail("토큰이 재발행 되었습니다.")
                    .data(token)
                    .build();
        } else {
            throw new ApiException(ExceptionEnum.NOT_MATCH_TOKEN);
        }

    }

    public ApiResponseDTO deleteRefreshToken(Principal principal) {
        try {
            refreshTokenInfoRedisRepository.deleteById(principal.getName());
            return ApiResponseDTO.builder()
                    .message("로그아웃")
                    .detail("로그아웃이 완료되었습니다.")
                    .build();
        } catch (ApiException e) {
            throw new ApiException(ExceptionEnum.CANNOT_LOGOUT); // 로그아웃 불가 메시지
        }
    }


    public ApiResponseDTO changeUser(ChangeUserDTO changeUserDTO, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        if (user == null)
            throw new AuthException(ExceptionEnum.NOT_EXIT_USER);
        user.setPassword(passwordEncoder.encode(changeUserDTO.getPassword()));
        user.setName(changeUserDTO.getName());
        user.setPhoneNumber(changeUserDTO.getPhoneNumber());

        return ApiResponseDTO.builder()
                .message("사용자 정보 변경")
                .detail("사용자 정보를 변경하였습니다.")
                .data(userRepository.save(user))
                .build();
    }

    public ApiResponseDTO findEmail(FindUserEmailDTO findUserEmailDTO) {
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
        
        return ApiResponseDTO.builder()
                .message("이메일 찾기")
                .detail("해당 정보에 맞는 이메일 입니다.")
                .data(String.valueOf(email))
                .build();

    }

}
