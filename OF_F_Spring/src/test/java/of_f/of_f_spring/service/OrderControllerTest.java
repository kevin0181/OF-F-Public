package of_f.of_f_spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.StoreCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreSideCategoryDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 변수 값 공유할 수 있게.
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static final String BASE_URL = "/api/v1/store/order";

    ObjectMapper objectMapper = new ObjectMapper();

    Long storeSeq = null;

    @Order(1)
    @Test
    @DisplayName("가맹점 메뉴 정보 가져오기(고객용)")
    public void 가맹점_메뉴_정보_가져오기() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/qr")
                        .param("storeSeq", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    storeSeq = jsonObject.getLong("seq");

                })
                .andDo(print()); // test 응답 결과에 대한 모든 내용 출력
    }

    @Order(2)
    @Test
    @DisplayName("가맹점 주문하기")
    public void 가맹점_주문하기() throws Exception {
        StoreOrderDTO storeOrderDTO = StoreOrderDTO.builder()
                .storeSeq(storeSeq)
                .id("123123")
                .kind(1l)
                .orderNumber("주문 번호")
                .totalPrice("10000")
                .place(1)
                .status(0)
                .payStatus(0)
                .build();


        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/qr")
                        .param("qrId", "1QR")
                        .content(objectMapper.writeValueAsString(storeOrderDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                })
                .andDo(print()); // test 응답 결과에 대한 모든 내용 출력
    }

}
