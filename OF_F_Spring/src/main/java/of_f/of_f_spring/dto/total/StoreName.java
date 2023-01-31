package of_f.of_f_spring.dto.total;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreName {
    private Long seq;
    private String name;
    private String address;
    private String detailAddress;
    private int status;
}
