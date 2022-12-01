package of_f.of_f_spring.dto.store.menu;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class StoreMenuDTO {
    private Long seq;

    @NotNull(message = "카테고리를 지정해야합니다.")
    private Long storeCategorySeq;

    @Size(min = 0, max = 30, message = "메뉴 이름은 0~30자 사이입니다.")
    private String name;

    @NotNull(message = "메뉴 가격은 0원 이상이어야합니다. 빈 값 금지")
    @NotBlank(message = "메뉴 가격은 0원 이상이어야합니다. 빈 값 금지")
    private String price;

    private boolean status;

    private boolean soldOutStatus;

    private List<StoreMenuImgDTO> storeMenuImgs;

    private List<StoreMSDTO> storeMSs;

}
