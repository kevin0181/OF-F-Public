package of_f.of_f_spring.dto.store.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class StoreMenuImgDTO {
    private Long seq;
    private Long storeMenuSeq;
    private String name;
    private String url;
    private String extension;
    private String date;
}
