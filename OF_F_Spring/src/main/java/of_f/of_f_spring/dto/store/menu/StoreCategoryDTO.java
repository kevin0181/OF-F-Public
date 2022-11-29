package of_f.of_f_spring.dto.store.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreCategoryDTO {
    private Long seq;
    private Long storeSeq;
    private String name;
    private boolean status;
    private List<StoreMenuDTO> storeMenus;
}
