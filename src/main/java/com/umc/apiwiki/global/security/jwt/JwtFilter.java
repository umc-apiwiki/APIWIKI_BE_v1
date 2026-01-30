package com.umc.apiwiki.global.security.jwt;

import com.umc.apiwiki.global.security.userdetails.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j // 로그 출력을 위해 추가
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // 토큰 유효성 검증
            if (jwtUtil.isValid(token)) {
                String email = jwtUtil.getEmail(token);

                // [핵심 수정] try-catch로 감싸서 예외가 터져도 필터가 중단되지 않게 함
                try {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception e) {
                    // 유저를 찾지 못했거나 에러가 발생
                    // 여기서 500 에러를 내지 않고, 그냥 "인증 실패" 상태로 다음 필터로 넘김
                    // 다른 필터에서 해당 요청을 처리한다
                    log.warn("JwtFilter - 유저 인증 실패: {}", e.getMessage());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}