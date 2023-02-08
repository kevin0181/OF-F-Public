package of_f.of_f_spring.controller.store;

import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.service.store.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/store/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/qr")
    public ApiResponseDTO storeMenuList(@RequestParam Long storeSeq, @RequestParam String qrId) { // 가맹점 메뉴 가져오는 부분 (고객용)
        return orderService.getStoreMenuList(storeSeq, qrId);
    }

    @PostMapping("/qr")
    public ApiResponseDTO orderQR(@RequestParam String qrId,
                                  @RequestBody @Valid StoreOrderDTO storeOrderDTO) {
        return orderService.orderQRService(qrId, storeOrderDTO);
    }

    @GetMapping("/search")
    public ApiResponseDTO searchStore(@RequestParam String storeName) {
        return orderService.searchStore(storeName);
    }

    @PostMapping("/pay/before")
    public ApiResponseDTO payBefore(@RequestParam Long storeId,
                                    @RequestParam String qrId,
                                    @RequestBody StoreOrderDTO storeOrderDTO) {
        return null;
    }

}
