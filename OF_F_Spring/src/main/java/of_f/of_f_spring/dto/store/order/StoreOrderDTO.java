package of_f.of_f_spring.dto.store.order;

import lombok.*;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreOrderDTO {
    private Long seq;
    private Long storeSeq;
    private String storeQRId;
    private String id;
    private Long kind;
    private String orderNumber;

    private String totalPrice;
    private String cancelAfterPrice;
    private Timestamp date;
    private Integer place;
    private Integer status;
    private Integer payStatus;
    private String phoneNumber;
    private String email;
    private Boolean emailReceiveStatus;
    private Boolean phoneNumberReceiveStatus;

    private OrderUserDTO user;
    private StoreOrderPgInfoDTO storeOrderPgInfo;
    private StoreOrderVanInfoDTO storeOrderVanInfo;
    private List<StoreOrderMenuDTO> storeOrderMenus;
}
