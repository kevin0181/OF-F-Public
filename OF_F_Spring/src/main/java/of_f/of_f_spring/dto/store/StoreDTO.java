package of_f.of_f_spring.dto.store;

import lombok.*;
import of_f.of_f_spring.domain.entity.store.QRStoreInfo;
import of_f.of_f_spring.dto.user.UserDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private Long seq;
    private Long userSeq;

    @NotNull(message = "가게 이름을 작성해주세요.")
    @NotBlank(message = "가게 이름을 작성해주세요.")
    @Size(min = 1, max = 20, message = "가게 이름은 1~20자 사이어야 합니다.")
    private String name;

    @NotNull(message = "사업자 번호를 입력해주세요.")
    @NotBlank(message = "사업자 번호를 입력해주세요.")
    @Size(min = 1, max = 15, message = "사업자 번호는 1~15자 사이어야 합니다.")
    private String businessNumber;

    @NotNull(message = "주소를 입력해주세요.")
    @NotBlank(message = "주소를 입력해주세요.")
    @Size(min = 1, max = 20, message = "주소는 1~20자 사이어야 합니다.")
    private String address;

    @NotNull(message = "상세 주소를 입력해주세요.")
    @NotBlank(message = "상세 주소를 입력해주세요.")
    @Size(min = 1, max = 35, message = "상세 주소는 1~35자 사이어야합니다.")
    private String detailAddress;

    private String accountBankName;
    private String accountNumber;
    private String depositDate;
    private String businessPhoneNumber;
    private String openDate;
    private int status;
}
