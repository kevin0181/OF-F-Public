package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.AuthException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.user.FindUserPasswordDTO;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import of_f.of_f_spring.repository.jwt.RefreshTokenInfoRedisRepository;
import of_f.of_f_spring.repository.user.EmailTokenRedisRepository;
import of_f.of_f_spring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailTokenRedisRepository emailTokenRedisRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenInfoRedisRepository refreshTokenInfoRedisRepository;

    public ApiResponseDTO saveEmailToken(EmailToken emailToken) {

        EmailToken getEmailToken = emailTokenRedisRepository.save(createRandomToken(emailToken.getEmail()));

        if (getEmailToken == null)
            throw new AuthException(ExceptionEnum.NOT_EXIT_USER);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(getEmailToken.getEmail());
            mimeMessageHelper.setSubject("off 회원가입 인증 메일입니다.");
            mimeMessageHelper.setText(createEmailTokenText(getEmailToken.getEmailToken()), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AuthException(ExceptionEnum.CAN_NOT_USE_MAIL);
        }

        return ApiResponseDTO.builder()
                .message("이메일 인증 발송 완료")
                .detail("인증 메일을 발송했습니다. 메일 인증을 완료해주세요.")
                .data(true)
                .build();
    }

    public ApiResponseDTO sendPasswordEmail(FindUserPasswordDTO findUserPasswordDTO) {

        EmailToken emailToken = emailTokenRedisRepository.save(createRandomToken(findUserPasswordDTO.getEmail()));

        if (emailToken == null)
            throw new AuthException(ExceptionEnum.NOT_EXIT_USER);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailToken.getEmail());
            mimeMessageHelper.setSubject("off 비밀번호 변경 메일입니다.");
            mimeMessageHelper.setText(createEmailFindPasswordText(emailToken.getEmailToken()), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AuthException(ExceptionEnum.CAN_NOT_USE_MAIL);
        }

        return ApiResponseDTO.builder()
                .message("비밀번호 변경 이메일 발송")
                .detail("인증 메일을 발송했습니다. 메일 인증을 완료해주세요.")
                .data(true)
                .build();
    }

    private String createEmailFindPasswordText(String token) {
        StringBuffer text = new StringBuffer();
        text.append("<a href=" + "" + token + ">" //리엑트에서 비밀번호 변경페이지로 다시 리다이렉트
                + "off 비밀번호 변경하러가기 (" + token + ")" + "</a>");

        return String.valueOf(text);
    }

    private String createEmailTokenText(String token) {

        StringBuffer text = new StringBuffer();
        text.append("<a href=" + "" + token + ">" //리엑트에서 회원가입 페이지로 리다이렉트
                + "off 인증하러가기 (" + token + ")" + "</a>");

        return String.valueOf(text);

    }

    public EmailToken createRandomToken(String email) {
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }

        return EmailToken.builder()
                .email(email)
                .emailToken(String.valueOf(temp))
                .build();

    }

    public ApiResponseDTO checkFindPasswordToken(String Authorization, String emailToken, UserLoginDTO userLoginDTO) {

        if (emailToken == null || emailToken.equals("")) {
            throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN);
        }

        try {
            EmailToken checkEmailToken = emailTokenRedisRepository.findById(emailToken).get();

            if (checkEmailToken == null)
                throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN); //토큰 없음 이메일 인증 다시

            emailTokenRedisRepository.deleteById(emailToken);

            User user = userRepository.findByEmail(userLoginDTO.getEmail());

            if (user == null)
                throw new AuthException(ExceptionEnum.NOT_EXIT_USER); //존재하지 않는 이메일 일때

            user.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));

            userRepository.save(user);

            if (Authorization != null) // 로그인 되어있던 상태
                refreshTokenInfoRedisRepository.deleteById(Authorization); // -> 로그아웃으로 만듬


            return ApiResponseDTO.builder()
                    .message("비밀번호 변경")
                    .detail("비밀번호 변경이 완료 되었습니다. 로그인해주세요.")
                    .data(true)
                    .build();

        } catch (NoSuchElementException e) {
            throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN);
        }

    }
}
