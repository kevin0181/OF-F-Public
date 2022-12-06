package of_f.of_f_spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.user.ChangeUserDTO;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 변수 값 공유할 수 있게.
public class StoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static final String BASE_URL = "/api/v1/store";

    ObjectMapper objectMapper = new ObjectMapper();

    TokenInfo tokenInfo = null;

    @Order(1)
    @Test
    @DisplayName("로그인")
    public void 로그인() throws Exception {

        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .email("test1@test1.com")
                .password("test1234@")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/n/login")
                        .content(objectMapper.writeValueAsString(userLoginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    tokenInfo = TokenInfo.builder()
                            .grantType(jsonObject.getString("grantType"))
                            .accessToken(jsonObject.getString("accessToken"))
                            .refreshToken(jsonObject.getString("refreshToken"))
                            .build();
                })
                .andDo(print()); // test 응답 결과에 대한 모든 내용 출력
    }

    Long storeSeq = null;

    @Order(2)
    @Test
    @DisplayName("가맹점 신청")
    public void 가맹점_신청() throws Exception {

        StoreDTO storeDTO = StoreDTO.builder()
                .name("test 가게")
                .businessNumber("test 사업자번호.")
                .address("test 주소")
                .detailAddress("test 상세 주소")
                .businessPhoneNumber("test 가게주소")
                .build();


        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/app/req")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getRefreshToken())
                        .content(objectMapper.writeValueAsString(storeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    storeSeq = jsonObject.getLong("seq");
                })
                .andDo(print());
    }

    TokenInfo adminToken = null;

    @Order(3)
    @Test
    @DisplayName("최고 관리자 로그인")
    public void 최고_관리자_로그인() throws Exception {

        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .email("kevin0181@naver.com")
                .password("kevin1141@")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/n/login")
                        .content(objectMapper.writeValueAsString(userLoginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    adminToken = TokenInfo.builder()
                            .grantType(jsonObject.getString("grantType"))
                            .accessToken(jsonObject.getString("accessToken"))
                            .refreshToken(jsonObject.getString("refreshToken"))
                            .build();
                })
                .andDo(print()); // test 응답 결과에 대한 모든 내용 출력
    }

    @Order(4)
    @Test
    @DisplayName("가맹점 신청자 권한 변경(가맹점 admin 으로)")
    public void 가맹점_신청자_권한_변경() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/admin/user/role")
                        .header("Authorization", adminToken.getGrantType() + " " + adminToken.getRefreshToken())
                        .param("email", "test1@test1.com")
                        .param("roleId", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }

    @Order(5)
    @Test
    @DisplayName("가맹점 신청 응답 (승인)")
    public void 가맹점_신청_응답() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/admin/app/res")
                        .header("Authorization", adminToken.getGrantType() + " " + adminToken.getRefreshToken())
                        .param("storeId", String.valueOf(storeSeq))
                        .param("status", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }

    @Order(6)
    @Test
    @DisplayName("가맹점 정보 가져오기 (가맹점 관리자 전용)")
    public void 가맹점_정보_가져오기() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/admin")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getRefreshToken())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }


}
