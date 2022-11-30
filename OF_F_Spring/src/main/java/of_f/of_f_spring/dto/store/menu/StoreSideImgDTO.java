package of_f.of_f_spring.dto.store.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class StoreSideImgDTO {
    private Long seq;
    private Long storeSideMenuSeq;
    private String id;
    private String name;
    private String url;
    private String extension;
    private String date;
}
