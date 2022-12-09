package of_f.of_f_spring.dto.store.qr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreQRIdDTO {
    private Long seq;
    private Long storeSeq;
    private String id;
}
