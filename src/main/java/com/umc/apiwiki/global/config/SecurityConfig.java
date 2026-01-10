package com.umc.apiwiki.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        // H2 Console이 켜져있을 경우를 대비한 MvcRequestMatcher
        // (스프링 부트 3.x부터는 H2 콘솔 제어 시 Introspector를 사용하는 것이 권장됨)
        MvcRequestMatcher h2RequestMatcher = new MvcRequestMatcher(introspector, "/**");
        h2RequestMatcher.setServletPath("/h2-console");

        http
                // 1. CSRF 비활성화
                .csrf((csrf) -> csrf
                        .disable()
                )

                // 2. Form & Basic 인증 비활성화
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable())

                // 3. 요청 권한 설정 (핵심 변경 포인트)
                .authorizeHttpRequests((auth) -> auth
                        // [안전 장치] H2 콘솔 요청은 "spring.h2.console.enabled = true" 일 때만 허용
                        .requestMatchers(PathRequest.toH2Console()).permitAll()

                        // Swagger 및 헬스 체크 허용
                        .requestMatchers("/health", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // 나머지는 인증 필요
                        .anyRequest().authenticated()
                )

                // 4. X-Frame-Options 설정 (보안을 위해 필요)
                .headers((headers) -> headers
                        .frameOptions((frame) -> {
                            // 배포 환경에서 H2 콘솔을 아예 안 쓴다면 sameOrigin()을 써서 보안을 지킴
                            // 로컬에서만 frame.disable() 효과를 내기 위해 로직 분기 불필요 (H2 요청에 대해서만 예외처리)
                            frame.sameOrigin();
                        })
                )

                // 5. 세션 설정
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}