package of_f.of_f_spring.service.store;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.domain.entity.store.order.StoreOrderPgInfo;
import of_f.of_f_spring.domain.exception.OrderException;
import of_f.of_f_spring.domain.exception.OrderExceptionEnum;
import of_f.of_f_spring.domain.mapper.store.StoreMapper;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.dto.total.StoreName;
import of_f.of_f_spring.repository.store.StoreMenuRepository;
import of_f.of_f_spring.repository.store.StoreOrderRepository;
import of_f.of_f_spring.repository.store.StoreQRIdRepository;
import of_f.of_f_spring.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreOrderRepository storeOrderRepository;

    @Autowired
    private StoreQRIdRepository storeQRIdRepository;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @Value("${imp.key}")
    private String IMPORT_IMP_KEY;

    @Value("${imp.secret}")
    private String IMPORT_IMP_SECRET;

    @Transactional
    public ApiResponseDTO getStoreMenuList(Long storeSeq, String qrId) {
        Store store = storeRepository.findById(storeSeq).orElse(null);

        for (int i = 0; i < store.getStoreQRIds().size(); i++) {
            if (store.getStoreQRIds().get(i).getQrId().equals(qrId)) {
                StoreDTO storeDTO = StoreMapper.instance.storeToStoreDTOByOrderUser(store);
                return ApiResponseDTO.builder()
                        .message("가맹점 메뉴 정보")
                        .detail("가맹점 메뉴 정보 입니다.")
                        .data(storeDTO)
                        .build();
            }
        }

        throw new OrderException(OrderExceptionEnum.DOES_NOT_EXIST_QR_ID);
    }

    public ApiResponseDTO searchStore(String storeName) {
        List<Store> storeList = storeRepository.findAllByNameContaining(storeName);

        List<StoreName> storeNames = StoreMapper.instance.storeToStoreName(storeList);

        return ApiResponseDTO.builder()
                .message("가게 정보")
                .detail("")
                .data(storeNames)
                .build();
    }

    public ApiResponseDTO beforeSaveData(StoreOrderDTO storeOrderDTO) {

        String merchant_uid = randomOrderId();
        storeOrderDTO.setId(merchant_uid); //주문번호 선 저장
        storeOrderDTO.setStatus(0);
        storeOrderDTO.setPayStatus(0);
        storeOrderDTO.setCancelAfterPrice(storeOrderDTO.getTotalPrice());
        storeOrderDTO.setStoreOrderPgInfo(null);
        storeOrderDTO.setStoreOrderVanInfo(null);

        return ApiResponseDTO.builder()
                .message("주문 결제 전 데이터")
                .detail("")
                .data(storeOrderRepository.save(StoreMapper.instance.storeOrderDTOToStoreOrder(storeOrderDTO)))
                .build();
    }

    public String getAccessToken() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); //보낼 데이터 담는 부분
        params.add("imp_key", IMPORT_IMP_KEY);
        params.add("imp_secret", IMPORT_IMP_SECRET);

        HttpHeaders headers = new HttpHeaders(); // 보낼 헤더 담는 부분

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers); //두개 합침

        RestTemplate rt = new RestTemplate();

        JSONParser jsonParse = new JSONParser();
        try {

            try {
                ResponseEntity<String> response = rt.exchange(
                        "https://api.iamport.kr/users/getToken", //{요청할 서버 주소}
                        HttpMethod.POST, //{요청할 방식}
                        entity, // {요청할 때 보낼 데이터}
                        String.class
                );

                JSONObject resObj = (JSONObject) jsonParse.parse(response.getBody());
                JSONObject getTokenObj = (JSONObject) jsonParse.parse(String.valueOf(resObj.get("response")));

                return (String) getTokenObj.get("access_token");

            } catch (HttpClientErrorException e) {
                JSONObject obj = (JSONObject) jsonParse.parse(e.getResponseBodyAsString());
                System.err.println(obj);
                throw new OrderException(OrderExceptionEnum.CAN_NOT_FIND_ORDER_DATA);
            } catch (HttpServerErrorException e) {
                JSONObject obj = (JSONObject) jsonParse.parse(e.getResponseBodyAsString());
                System.err.println(obj);
                throw new OrderException(OrderExceptionEnum.CAN_NOT_FIND_ORDER_DATA);
            }
        } catch (ParseException e) {
            System.err.println(e);
            throw new OrderException(OrderExceptionEnum.CAN_NOT_FIND_ORDER_DATA);
        }

    }

    public JSONObject paymentData(String accessToken, String impUid) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); //보낼 데이터 담는 부분

        HttpHeaders headers = new HttpHeaders(); // 보낼 헤더 담는 부분
        headers.add("Authorization", accessToken);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers); //두개 합침

        RestTemplate rt = new RestTemplate();

        JSONParser jsonParse = new JSONParser();
        try {
            try {
                ResponseEntity<String> response = rt.exchange(
                        "https://api.iamport.kr/payments/" + impUid, //{요청할 서버 주소}
                        HttpMethod.GET, //{요청할 방식}
                        entity, // {요청할 때 보낼 데이터}
                        String.class
                );

                JSONObject resObj = (JSONObject) jsonParse.parse(response.getBody());
                JSONObject paymentData = (JSONObject) jsonParse.parse(String.valueOf(resObj.get("response")));

                return paymentData;

            } catch (HttpClientErrorException e) {
                JSONObject obj = (JSONObject) jsonParse.parse(e.getResponseBodyAsString());
                System.err.println(obj);
                throw new OrderException(OrderExceptionEnum.CAN_NOT_FIND_ORDER_DATA);
            } catch (HttpServerErrorException e) {
                JSONObject obj = (JSONObject) jsonParse.parse(e.getResponseBodyAsString());
                System.err.println(obj);
                throw new OrderException(OrderExceptionEnum.CAN_NOT_FIND_ORDER_DATA);
            }
        } catch (ParseException e) {
            System.err.println(e);
            throw new OrderException(OrderExceptionEnum.CAN_NOT_FIND_ORDER_DATA);
        }
    }

    public ApiResponseDTO checkPayment(JSONObject paymentData, String merchantUid) {

        StoreOrder order = storeOrderRepository.findById(merchantUid);

        if (order.getStatus() == 1 && order.getPayStatus() == 1) {
            switch (String.valueOf(paymentData.get("status"))) {
                case "ready":
                    return ApiResponseDTO.builder()
                            .message("가상계좌 발급 완료")
                            .detail("가상계좌가 발급되었습니다.")
                            .data(StoreMapper.instance.storeOrderToStoreOrderDTO(order))
                            .build();
                case "paid":
                    return ApiResponseDTO.builder()
                            .message("결제완료")
                            .detail("결제가 완료되었습니다.")
                            .data(StoreMapper.instance.storeOrderToStoreOrderDTO(order))
                            .build();
                case "failed":
                    return ApiResponseDTO.builder()
                            .message("결제실패")
                            .detail("결제를 실패하였습니다.")
                            .data(StoreMapper.instance.storeOrderToStoreOrderDTO(order))
                            .build();

            }

        } else {

            if (paymentData.get("merchant_uid").equals(merchantUid) && Integer.parseInt(order.getTotalPrice()) == Integer.parseInt(String.valueOf(paymentData.get("amount")))) {
                // => 결제 성공했으니깐 결제 정보 저장 및 리턴해주기.

                StoreOrderPgInfo storeOrderPgInfo = StoreOrderPgInfo.builder()
                        .storeOrderSeq(order.getSeq())
                        .impUid(String.valueOf(paymentData.get("merchant_uid")))
                        .merchantUid(merchantUid)
                        .pgProvider(String.valueOf(paymentData.get("pg_provider")))
                        .payMethod(String.valueOf(paymentData.get("pay_method")))
                        .status(String.valueOf(paymentData.get("status")))
                        .cardName(String.valueOf(paymentData.get("card_name")))
                        .cardNumber(String.valueOf(paymentData.get("card_number")))
                        .build();

                order.setStoreOrderPgInfo(storeOrderPgInfo);

                switch (String.valueOf(paymentData.get("status"))) {
                    case "ready":
                        return ApiResponseDTO.builder()
                                .message("가상계좌 발급 완료")
                                .detail("가상계좌가 발급되었습니다.")
                                .data(StoreMapper.instance.storeOrderToStoreOrderDTO(order))
                                .build();
                    case "paid":
                        order.setPayStatus(1);
                        order = storeOrderRepository.save(order); // 검증된 결제 데이터 저장
                        return ApiResponseDTO.builder()
                                .message("결제완료")
                                .detail("결제가 완료되었습니다.")
                                .data(StoreMapper.instance.storeOrderToStoreOrderDTO(order))
                                .build();
                    case "failed":
                        return ApiResponseDTO.builder()
                                .message("결제실패")
                                .detail("결제를 실패하였습니다.")
                                .data(StoreMapper.instance.storeOrderToStoreOrderDTO(order))
                                .build();

                }
            }
        }
        throw new OrderException(OrderExceptionEnum.FAIL_PAY);
    }

    public String randomOrderId() {

        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmm");
        String result = df.format(today);

        return result + "_" + temp;
    }


    public ApiResponseDTO deleteOrder(String merchantUid) {
        StoreOrder order = storeOrderRepository.findById(merchantUid);

        storeOrderRepository.delete(order);

        return ApiResponseDTO.builder()
                .message("주문 실패 데이터 삭제")
                .detail("주문 실패로 인해 데이터 삭제")
                .build();
    }
}
