package of_f.of_f_spring.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindUserPasswordDTO {
    @NotNull
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private boolean status;
}
