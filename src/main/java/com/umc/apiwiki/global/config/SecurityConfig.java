package com.umc.apiwiki.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 비활성화 (REST API 서버는 세션 기반이 아니라 보통 끕니다)
                .csrf((auth) -> auth.disable())

                // 2. 기본 로그인 폼 & HTTP Basic 인증 비활성화 (우리는 JWT 쓸 거니까)
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable())

                // 3. 요청에 대한 권한 설정 (핵심!)
                .authorizeHttpRequests((auth) -> auth
                        // "/health"는 누구나 들어올 수 있게 허용
                        // Swagger 관련 주소들도 허용해뒀습니다 (나중에 확인 편하도록)
                        .requestMatchers("/health", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // 그 외 모든 요청은 인증된 사용자만 접근 가능 (지금은 로그인 기능 없으니 사실상 다 막힘)
                        .anyRequest().authenticated()
                )

                // 4. 세션 관리 상태 없음으로 설정 (JWT는 Stateless가 원칙)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}