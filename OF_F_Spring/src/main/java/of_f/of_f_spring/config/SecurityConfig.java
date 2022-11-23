package of_f.of_f_spring.config;

import of_f.of_f_spring.config.jwt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity //spring security 활성화
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //http 비활성화
                .cors().and() //cors활성화
                .csrf().disable() //jwt토큰을 사용하므로 csrf비활성화 -> localstorage에 저장시 비활성화 아니면 활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//security에서 사용하는 session 비활성화
                .and()
                .authorizeRequests() //인증절차 설정 시작
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/of_f/main/**").permitAll()
                .antMatchers("/api/v1/of_f/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/qr/store/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()  // security의 기본 로그인 화면을 비활성화
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //비밀번호 해쉬 암호화
        return new BCryptPasswordEncoder();
    }

}
