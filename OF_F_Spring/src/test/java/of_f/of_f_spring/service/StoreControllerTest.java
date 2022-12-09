package of_f.of_f_spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.StoreCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.dto.user.ChangeUserDTO;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;
import java.util.Map;

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

    TokenInfo adminToken = null;

    Long storeSeq = null;

    Long categorySeq = null;

    Long menuSeq = null;

    Long storeQRInfoSeq = null;

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
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
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
                        .header("Authorization", adminToken.getGrantType() + " " + adminToken.getAccessToken())
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
                        .header("Authorization", adminToken.getGrantType() + " " + adminToken.getAccessToken())
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
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    JSONArray jsonArray = jsonObject.getJSONArray("stores");
                    jsonObject = jsonArray.getJSONObject(0);
                    storeSeq = jsonObject.getLong("seq");
                })
                .andDo(print());
    }

    @Order(7)
    @Test
    @DisplayName("가맹점 카테고리 생성")
    public void 가맹점_카테고리_생성() throws Exception {

        StoreCategoryDTO storeCategoryDTO = StoreCategoryDTO.builder()
                .storeSeq(storeSeq)
                .name("test 카테고리")
                .status(false)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/admin/category")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .param("status", "insert")
                        .content(objectMapper.writeValueAsString(storeCategoryDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    categorySeq = jsonObject.getLong("seq");
                })
                .andDo(print());
    }

    @Order(8)
    @Test
    @DisplayName("가맹점 카테고리 변경")
    public void 가맹점_카테고리_변경() throws Exception {

        StoreCategoryDTO storeCategoryDTO = StoreCategoryDTO.builder()
                .seq(categorySeq)
                .storeSeq(storeSeq)
                .name("test 카테고리 변경")
                .status(false)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/admin/category")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .param("status", "update")
                        .content(objectMapper.writeValueAsString(storeCategoryDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    categorySeq = jsonObject.getLong("seq");
                })
                .andDo(print());
    }

    @Order(8)
    @Test
    @DisplayName("가맹점 카테고리 삭제")
    public void 가맹점_카테고리_삭제() throws Exception {

        StoreCategoryDTO storeCategoryDTO = StoreCategoryDTO.builder()
                .seq(categorySeq)
                .storeSeq(storeSeq)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/admin/category")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .param("status", "delete")
                        .content(objectMapper.writeValueAsString(storeCategoryDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }

    @Order(9)
    @Test
    @DisplayName("가맹점 메뉴 추가(이미지 제외)")
    public void 가맹점_메뉴_추가() throws Exception {

        MockMultipartFile jsonFile = new MockMultipartFile("menu", "", "application/json", ("{\n" +
                "    \"storeCategorySeq\":\"2\",\n" +
                "    \"name\":\"1\",\n" +
                "    \"price\":\"1\",\n" +
                "    \"status\":\"false\"\n" +
                "}").getBytes());


        mockMvc.perform(MockMvcRequestBuilders
                        .multipart(BASE_URL + "/admin/menu")
                        .file(jsonFile)
                        .param("status", "insert")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
//                .andDo(result -> {
//                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
//                    jsonObject = new JSONObject(jsonObject.getString("data"));
//                    menuSeq = jsonObject.getLong("seq");
//                })
                .andDo(print());
    }


    @Order(10)
    @Test
    @DisplayName("가맹점 메뉴 추가(이미지 포함)")
    public void 가맹점_메뉴_추가2() throws Exception {


        final String fileName = "dog"; //파일명
        final String contentType = "jpeg"; //파일타입
        final String filePath = "/Users/yuyeongbin/of_f/" + fileName + "." + contentType; //파일경로
        FileInputStream fileInputStream = new FileInputStream(filePath);

        MockMultipartFile jsonFile = new MockMultipartFile("menu", "", "application/json", ("{\n" +
                "    \"storeCategorySeq\":\"2\",\n" +
                "    \"name\":\"1\",\n" +
                "    \"price\":\"1\",\n" +
                "    \"status\":\"false\"\n" +
                "}").getBytes());
        MockMultipartFile secondFile = new MockMultipartFile(
                "img",
                fileName + "." + contentType,
                contentType,
                fileInputStream);


        mockMvc.perform(MockMvcRequestBuilders
                        .multipart(BASE_URL + "/admin/menu")
                        .file(jsonFile)
                        .file(secondFile)
                        .param("status", "insert")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    menuSeq = jsonObject.getLong("seq");
                })
                .andDo(print());
    }

    @Order(11)
    @Test
    @DisplayName("가맹점 메뉴 변경(이미지 제외)")
    public void 가맹점_메뉴_변경() throws Exception {

        MockMultipartFile jsonFile = new MockMultipartFile("menu", "", "application/json", ("{\n" +
                "    \"seq\":\"" + menuSeq + "\",\n" +
                "    \"storeCategorySeq\":\"2\",\n" +
                "    \"name\":\"" + "변경된 메뉴" + "\",\n" +
                "    \"price\":\"010101\",\n" +
                "    \"status\":\"true\"\n" +
                "}").getBytes());


        mockMvc.perform(MockMvcRequestBuilders
                        .multipart(BASE_URL + "/admin/menu")
                        .file(jsonFile)
                        .param("status", "update")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }

    @Order(12)
    @Test
    @DisplayName("가맹점 메뉴 삭제")
    public void 가맹점_메뉴_삭제() throws Exception {

        MockMultipartFile jsonFile = new MockMultipartFile("menu", "", "application/json", ("{\n" +
                "    \"seq\":\"" + menuSeq + "\",\n" +
                "    \"storeCategorySeq\":\"2\",\n" +
                "    \"name\":\"" + "변경된 메뉴" + "\",\n" +
                "    \"price\":\"010101\",\n" +
                "    \"status\":\"true\"\n" +
                "}").getBytes());


        mockMvc.perform(MockMvcRequestBuilders
                        .multipart(BASE_URL + "/admin/menu")
                        .file(jsonFile)
                        .param("status", "delete")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }


    @Order(13)
    @Test
    @DisplayName("가맹점 QR 정보 등록")
    public void 가맹점_QR_정보_등록() throws Exception {

        QRStoreInfoDTO qrStoreInfoDTO = QRStoreInfoDTO.builder()
                .storeSeq(storeSeq)
                .qrPayStatus(false)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/api/v1/admin/store/QR/info")
                        .param("status", "insert")
                        .content(objectMapper.writeValueAsString(qrStoreInfoDTO))
                        .header("Authorization", adminToken.getGrantType() + " " + adminToken.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(result -> {
                    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                    jsonObject = new JSONObject(jsonObject.getString("data"));
                    storeQRInfoSeq = jsonObject.getLong("seq");
                    storeSeq = jsonObject.getLong("storeSeq");
                })
                .andDo(print());
    }


    @Order(13)
    @Test
    @DisplayName("가맹점 QR 정보 변경")
    public void 가맹점_QR_정보_변경() throws Exception {

        QRStoreInfoDTO qrStoreInfoDTO = QRStoreInfoDTO.builder()
                .seq(storeQRInfoSeq)
                .storeSeq(storeSeq)
                .qrPayStatus(false)
                .qrSize(30)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/api/v1/admin/store/QR/info")
                        .param("status", "insert")
                        .content(objectMapper.writeValueAsString(qrStoreInfoDTO))
                        .header("Authorization", adminToken.getGrantType() + " " + adminToken.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }

}
