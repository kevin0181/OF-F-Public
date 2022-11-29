package of_f.of_f_spring.dto.user;

import lombok.*;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private Long seq;
    private String email;
    private String name;
    private String phoneNumber;
    private int userStatus;
    private boolean emailReceiveStatus;
    private boolean phoneNumberReceiveStatus;
    private String createTime;
    private List<UserRoleDTO> userRoles;

    private List<StoreDTO> stores;
    private List<StoreOrderDTO> storeOrders;
}
