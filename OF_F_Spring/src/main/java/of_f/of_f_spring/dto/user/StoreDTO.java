package of_f.of_f_spring.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDTO {
    private Long seq;
    private Long userSeq;
    private String name;
    private String businessNumber;
    private String address;
    private String detailAddress;
    private String accountNumber;
    private String depositDate;
    private String businessPhoneNumber;
    private String openDate;
    private int status;

    private UserDTO user;
}
