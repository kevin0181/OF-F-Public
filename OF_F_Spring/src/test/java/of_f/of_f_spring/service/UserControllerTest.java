package of_f.of_f_spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import of_f.of_f_spring.config.jwt.TokenInfo;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.dto.user.UserLoginDTO;
import of_f.of_f_spring.dto.user.UserSignInDTO;
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

import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 변수 값 공유할 수 있게.
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    private static final String BASE_URL = "/api/v1/auth";

    ObjectMapper objectMapper = new ObjectMapper();

    @Order(1)
    @Test
    @DisplayName("이메일 인증 토큰 보내기")
    public void 이메일_인증토큰_전송() throws Exception {

        EmailToken emailToken = EmailToken.builder()
                .email("kevin0181@naver.com")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/n/email/check")
                        .content(objectMapper.writeValueAsString(emailToken))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print()); // test 응답 결과에 대한 모든 내용 출력
    }


    @Order(2)
    @Test
    @DisplayName("회원가입")
    public void 회원가입() throws Exception {

        UserSignInDTO userSignInDTO = UserSignInDTO.builder()
                .email("test1@test1.com")
                .password("test1234@")
                .rePassword("test1234@")
                .name("test")
                .phoneNumber("01012341234")
                .emailReceiveStatus(true)
                .phoneNumberReceiveStatus(true)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/n/signIn")
                        .param("emailToken", "1BUiX6va0j2Sfq93CreN")
                        .content(objectMapper.writeValueAsString(userSignInDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print()); // test 응답 결과에 대한 모든 내용 출력
    }


    TokenInfo tokenInfo = null;

    @Order(3)
    @Test
    @DisplayName("로그인")
    public void 로그인() throws Exception {

        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .email("test1@test1.com")
                .password("test1234@")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/n/login")
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

    @Order(4)
    @Test
    @DisplayName("refresh 토큰 재발행")
    public void 토큰_재발행() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/y/refresh-token")
                        .header("Authorization", tokenInfo.getGrantType() + " " + tokenInfo.getRefreshToken())
                        .content(objectMapper.writeValueAsString(tokenInfo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andDo(print());
    }


}
