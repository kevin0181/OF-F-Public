package of_f.of_f_spring.controller.store;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import of_f.of_f_spring.domain.exception.OrderException;
import of_f.of_f_spring.domain.exception.OrderExceptionEnum;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.service.store.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/store/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/qr")
    public ApiResponseDTO storeMenuList(@RequestParam Long storeSeq, @RequestParam String qrId) { // 가맹점 메뉴 가져오는 부분 (고객용)
        return orderService.getStoreMenuList(storeSeq, qrId);
    }

    @GetMapping("/search")
    public ApiResponseDTO searchStore(@RequestParam String storeName) {
        return orderService.searchStore(storeName);
    }

    @PostMapping("/pay/before")
    public ApiResponseDTO payBefore(@RequestBody StoreOrderDTO storeOrderDTO) { //주문 전 저장
        return orderService.beforeSaveData(storeOrderDTO);
    }

    @PostMapping("/pay/after")
    public ApiResponseDTO payAfter(@RequestBody Map<String, String> bodyData) {

        if (bodyData.get("imp_uid") == null || bodyData.get("merchant_uid") == null)
            throw new OrderException(OrderExceptionEnum.CAN_NOT_FIND_ORDER_DATA);

        String token = orderService.getAccessToken(); // token 가져오기

        JSONObject paymentData = orderService.paymentData(token, bodyData.get("imp_uid")); //결제 내역 가져오기

        orderService.checkPayment(paymentData, bodyData.get("merchant_uid"));

        return null;
    }

    @PostMapping("/webhook/pay")
    public ApiResponseDTO payWebHook(@RequestBody Map<String, String> bodyData) {
        return null;
    }

}
