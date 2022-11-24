package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.exception.AuthException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import of_f.of_f_spring.dto.user.VerifyEmailInfo;
import of_f.of_f_spring.repository.user.EmailTokenRedisRepository;
import of_f.of_f_spring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.Principal;
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

    public boolean saveEmailToken(String email) {

        EmailToken emailToken = emailTokenRedisRepository.save(createRandomToken(email));

        if (emailToken == null) {
            return false;
        }

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("off 회원가입 인증 메일입니다.");
            mimeMessageHelper.setText(createEmailText(emailToken.getEmailToken()), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AuthException(ExceptionEnum.CAN_NOT_USE_MAIL);
        }

        return true;
    }

    private String createEmailText(String token) {

        StringBuffer text = new StringBuffer();
        text.append("<a href=" + "http://localhost/api/v1/auth/email/check/token?emailToken=" + token + ">"
                + "off 인증하러가기" + "</a>");

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


    public VerifyEmailInfo checkToken(String emailToken) {

        if (emailToken.equals("") || emailToken == null) {
            throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN);
        }

        try {
            EmailToken checkEmailToken = emailTokenRedisRepository.findById(emailToken).get();

            if (checkEmailToken == null)
                throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN);

            emailTokenRedisRepository.deleteById(emailToken);

            return VerifyEmailInfo.builder()
                    .email(checkEmailToken.getEmail())
                    .redirectURI("리다이렉트 uri")
                    .status(true)
                    .build();

        } catch (NoSuchElementException e) {
            throw new AuthException(ExceptionEnum.NOT_EXIT_EMAIL_TOKEN);
        }

    }

}
