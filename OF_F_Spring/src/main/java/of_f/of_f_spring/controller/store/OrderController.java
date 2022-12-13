package of_f.of_f_spring.controller.store;

import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.service.store.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/store/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/qr")
    public ApiResponseDTO storeMenuList(@RequestParam Long storeSeq) { // 가맹점 메뉴 가져오는 부분 (고객용)
        return orderService.getStoreMenuList(storeSeq);
    }
}
