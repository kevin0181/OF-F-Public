package of_f.of_f_spring.dto.user;

import lombok.*;
import of_f.of_f_spring.dto.store.StoreDTO;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInDTO {
    private Long seq;

    @NotNull
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotNull
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}",
            message = "비밀번호는 8~16자 영문, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

    @NotNull
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}",
            message = "비밀번호는 8~16자 영문, 숫자, 특수문자가 포함되어야 합니다.")
    private String rePassword;

    @NotNull
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min = 1, max = 6, message = "이름은 1~6자 이어야합니다.")
    private String name;

    @NotNull
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Size(min = 1, max = 15, message = "전화번호는 15자리를 넘을 수 없습니다.")
    @Pattern(regexp = "01{1}[016789]{1}[0-9]{7,8}", message = "전화번호는 - 가 없어야합니다.")
    private String phoneNumber;

    private int userStatus;

    @NotNull
    @AssertTrue(message = "이메일 수신 동의는 필수 입력 값입니다.")
    private boolean emailReceiveStatus;

    @NotNull
    @AssertTrue(message = "번호 수신 동의는 필수 입력 값입니다.")
    private boolean phoneNumberReceiveStatus;

    private List<UserRoleDTO> userRoles;
}
