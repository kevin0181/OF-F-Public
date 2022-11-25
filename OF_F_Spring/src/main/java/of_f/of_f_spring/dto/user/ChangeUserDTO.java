package of_f.of_f_spring.dto.user;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
public class ChangeUserDTO {
    @NotNull
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}",
            message = "비밀번호는 8~16자 영문, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;
    @NotNull
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min = 1, max = 6, message = "이름은 1~6자 이어야합니다.")
    private String name;
    @NotNull
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Size(min = 1, max = 15, message = "전화번호는 15자리를 넘을 수 없습니다.")
    @Pattern(regexp = "01{1}[016789]{1}[0-9]{7,8}", message = "전화번호는 - 가 없어야합니다.")
    private String phoneNumber;
}
