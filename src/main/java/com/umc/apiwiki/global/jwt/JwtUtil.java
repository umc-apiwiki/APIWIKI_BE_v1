package com.umc.apiwiki.global.jwt;

import com.umc.apiwiki.global.principal.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;          // 시크릿 키
    private final Duration accessExpiration;    // 접근 만료 시간

    /// 생성자
    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,                 // application.yml에서 비밀 키 값을 가져옴
            @Value("${jwt.token.expiration.access}") Long accessExpiration  // 유효 기간 값을 가져옴
    ) {
        // 가져온 문자열 비밀 키를 암호화 알고리즘에서 쓸 수 있는 객체 형태로 변환
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    /// 외부에서 호출하는 메소드
    /// 로그인 시 사용
    public String createAccessToken(CustomUserDetails user) {
        // 실제로 토큰이 만들어지는 내부 메소드
        return createToken(user.getUsername(), user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")));    }

    /// 외부에서 호출하는 메소드
    /// 회원가입 시 사용
    public String createAccessToken(String nickname, String role) {
        return createToken(nickname, role);
    }

    /// 토큰에서 정보를 추출하는 메소드
    public String getEmail(String token) {
        try {
            return getClaims(token).getPayload().getSubject(); // 전달받은 토큰의 주인(여기서는 이메일)을 가져온다
        } catch (JwtException e) {
            return null;
        }
    }

    /// 외부에서 호출하는 메소드
    /// 토큰의 유효성을 검증한다
    public boolean isValid(String token) {
        try {
            getClaims(token);   // 토큰 검증 메소드 호출
            return true;
        } catch (JwtException e) {  // 에러(만료, 위조 등) 발생 시 false 리턴
            return false;
        }
    }

    /// 실제로 토큰이 만들어지는 내부 메소드
    private String createToken(String nickname, String authorities) {
        Instant now = Instant.now();    // 현재 시간

        return Jwts.builder()
                .subject(nickname)        // 토큰의 주인 설정 (여기서는 이메일)
                .claim("role", authorities)         // 토큰의 권한
                .claim("nickname", nickname) // 사용자 닉네임
                .issuedAt(Date.from(now))           // 발급 시간
                .expiration(Date.from(now.plus(accessExpiration))) // 만료 시간
                .signWith(secretKey)    // 서버의 비밀 키로 서명
                .compact();             // .으로 구분된 문자열로 압축하여 반환
    }

    /// 토큰의 내부 정보를 열어보는 메소드
    /// 클라이언트가 가져온 토큰의 진위여부와 유효기간을 확인함
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)      // 비밀키로 서명한 게 맞는지
                .clockSkewSeconds(60)       // 서버 간 차이 60초 고려
                .build()
                .parseSignedClaims(token);  // 토큰 파싱
    }
}