package of_f.of_f_spring.dto.store.order;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserDTO {
        private Long seq;
        private String email;
        private String name;
        private String phoneNumber;
        private int userStatus;
        private boolean emailReceiveStatus;
        private boolean phoneNumberReceiveStatus;
        private String createTime;
}
