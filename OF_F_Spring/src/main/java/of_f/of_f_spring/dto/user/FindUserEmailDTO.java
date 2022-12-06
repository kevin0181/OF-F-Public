package of_f.of_f_spring.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindUserEmailDTO {

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
