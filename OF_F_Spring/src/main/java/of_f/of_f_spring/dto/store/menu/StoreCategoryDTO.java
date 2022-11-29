package of_f.of_f_spring.dto.store.menu;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class StoreCategoryDTO {
    private Long seq;
    private Long storeSeq;

    @Size(min = 0, max = 25, message = "카테고리 이름은 0~25자 사이입니다.")
    private String name;
    private boolean status;

    private List<StoreMenuDTO> storeMenus;
}
