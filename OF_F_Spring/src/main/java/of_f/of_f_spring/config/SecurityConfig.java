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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity //spring security 활성화
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //http 비활성화
                .cors().configurationSource(corsConfigurationSource())
                .and() //cors활성화
                .csrf().disable() //jwt토큰을 사용하므로 csrf비활성화 -> localstorage에 저장시 비활성화 아니면 활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//security에서 사용하는 session 비활성화
                .and()
                .authorizeRequests() //인증절차 설정 시작
                .antMatchers("/api/v1/auth/n/**").permitAll() // 사용자 || 토큰x
                .antMatchers("/api/v1/auth/y/**").hasAnyAuthority("ROLE_USER", "ROLE_TT_ADMIN", "ROLE_ST_ADMIN") // 사용자 || 토큰o
                .antMatchers("/api/v1/main/**").permitAll() // 모든 사용자
                .antMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_TT_ADMIN") // 최고 관리자
                .antMatchers("/api/v1/store/admin/**").hasAnyAuthority("ROLE_ST_ADMIN", "ROLE_TT_ADMIN") // 가맹점 관리자
                .antMatchers("/api/v1/store/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()  // security의 기본 로그인 화면을 비활성화
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //비밀번호 해쉬 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
