package of_f.of_f_spring.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.repository.jwt.RefreshTokenInfoRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    @Autowired
    private RefreshTokenInfoRedisRepository refreshTokenInfoRedisRepository;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenInfo generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        // Access Token 생성
        // 86400000 = 1일, 1800000 = 30분 , 2592000000l = 1달, 7일 = 604800000
        //초 : / 1000
        //분 : / (1000 * 60)
        //시 : / (1000 * 60 * 60)
        //달 : / (1000 * 60 * 60 * 24 * 30)
        Date issuedAt = new Date();

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");

        String accessToken = Jwts.builder()
                .setHeader(headers)
                .setSubject("accessToken")
                .claim("iss", "off")
                .claim("aud", authentication.getName())
                .claim("auth", authorities)
                .setExpiration(new Date(now + 1800000))
                .setIssuedAt(issuedAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setHeader(headers)
                .setSubject("refreshToken")
                .claim("iss", "off")
                .claim("aud", authentication.getName())
                .claim("auth", authorities)
                .claim("add", "ref")
                .setExpiration(new Date(now + 604800000))
                .setIssuedAt(issuedAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String token) {
        // 토큰 복호화
        Claims claims = parseClaims(token);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User((String) claims.get("aud"), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new ApiException(ExceptionEnum.INVALID_TOKEN_INFO);
        } catch (ExpiredJwtException e) {
            throw new ApiException(ExceptionEnum.TIMEOUT_TOKEN); //-> 토큰 재발행 요청
        } catch (UnsupportedJwtException e) {
            throw new ApiException(ExceptionEnum.INVALID_TOKEN_INFO);
        } catch (IllegalArgumentException e) {
            throw new ApiException(ExceptionEnum.INVALID_TOKEN_INFO);
        } catch (Exception e){
            throw new ApiException(ExceptionEnum.INVALID_TOKEN_INFO);
        }
    }

    public void saveToken(TokenInfo tokenInfo, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        refreshTokenInfoRedisRepository.save(
                RefreshTokenInfo.builder()
                        .refreshToken(tokenInfo.getRefreshToken()) //리프레시 토큰저장
                        .email(userDetails.getUsername()) //이메일이랑
                        .build()
        );

    }

    //만약 validation에서 accessToken이 기한 만료 오류가 났다면 refreshToken을 전송해서 새로 발급받기.
    public TokenInfo refreshToken(String refreshToken) {
        try {

            //refresh 토큰 복호화
            Authentication authentication = getAuthentication(refreshToken);

            //redis에 저장되어있는 refresh token을 가져옴
            RefreshTokenInfo getRedisRefreshToken = refreshTokenInfoRedisRepository.findById(authentication.getName()).get();

            TokenInfo refreshGetToken = null;
            if (refreshToken.equals(getRedisRefreshToken.getRefreshToken()))
                refreshGetToken = generateToken(authentication);

            saveToken(refreshGetToken, authentication);

            return refreshGetToken;

        } catch (NullPointerException e) {
            log.warn("does not exist Token"); // refresh 토큰이 존재하지 않음 (로그아웃 상태)
            throw new ApiException(ExceptionEnum.TOKEN_DOES_NOT_EXIT);
        } catch (SignatureException e) {
            log.warn("Invalid Token Info"); // (토큰이 틀렸을때) (위조 변조)
            throw new ApiException(ExceptionEnum.INVALID_TOKEN_INFO);
        } catch (NoSuchElementException e) {
            log.warn("no such Token value"); //토큰이 존재하지 않을때. (로그아웃 상태)
            throw new ApiException(ExceptionEnum.TOKEN_DOES_NOT_EXIT);
        }

    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
