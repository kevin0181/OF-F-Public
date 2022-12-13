package of_f.of_f_spring.dto.store.menu;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreMSDTO {
    private Long seq;
    private Long storeMenuSeq;
    private Long storeSideCategorySeq;

    private StoreSideCategoryDTO storeSideCategory;
}
