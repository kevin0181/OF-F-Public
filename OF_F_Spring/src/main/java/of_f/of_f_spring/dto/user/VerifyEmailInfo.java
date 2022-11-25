package of_f.of_f_spring.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyEmailInfo {
    private String email;
    private String redirectURI;
    private boolean status;
}
