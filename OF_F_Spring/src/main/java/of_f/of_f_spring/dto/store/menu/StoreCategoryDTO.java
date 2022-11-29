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
    @NotNull(message = "카테고리 이름을 입력해주세요.")
    @NotBlank(message = "카테고리 이름을 입력해주세요.")
    @Size(min = 1, max = 25, message = "카테고리 이름은 1~25자 사이입니다.")
    private String name;
    private boolean status;

    private List<StoreMenuDTO> storeMenus;
}
