package com.umc.apiwiki.domain.user.service.command;

import com.umc.apiwiki.domain.user.dto.UserReqDTO;
import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.domain.user.enums.Provider;
import com.umc.apiwiki.domain.user.repository.UserRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import com.umc.apiwiki.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화용
    private final JwtUtil jwtUtil;                  // 토큰 생성

    // 이메일 정규식 (간단한 예시)
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    // 비밀번호 정규식 (영문+숫자, 8자 이상)
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public UserResDTO.LoginRes signup(UserReqDTO.Signup dto) {
        // 1. 유효성 검증
        validateFormat(dto);

        // 2. 중복 검사
        if (userRepository.existsByEmail(dto.email())) {
            throw new GeneralException(GeneralErrorCode.DUPLICATE_EMAIL);
        }
        if (userRepository.existsByNickname(dto.nickname())) {
            throw new GeneralException(GeneralErrorCode.DUPLICATE_NICKNAME);
        }

        // 3. Entity 생성 및 저장
        User newUser = User.builder()
                .email(dto.email())
                .passwordHash(passwordEncoder.encode(dto.password())) // 비밀번호 암호화
                .nickname(dto.nickname())
                .name(null)
                .provider(Provider.LOCAL)
                .build();

        User savedUser = userRepository.save(newUser);

        // 4. 토큰 발급
        String accessToken = jwtUtil.createAccessToken(newUser.getEmail(), newUser.getNickname(), "ROLE_USER");

        // 5. 응답 반환
        return UserResDTO.LoginRes.builder()
                .memberId(savedUser.getId())
                .accessToken(accessToken)
                .nickname(savedUser.getNickname())
                .build();
    }

    private void validateFormat(UserReqDTO.Signup dto) {
        // 필수값 누락 및 빈 값("") 체크 (isBlank 사용)
        if (isInvalid(dto.email()) || isInvalid(dto.password()) || isInvalid(dto.nickname())) {
            throw new GeneralException(GeneralErrorCode.MISSING_INPUT_VALUE);
        }

        // DB 컬럼 길이 제한 방어
        // email(50자), nickname(20자) 제한을 넘으면 에러 발생
        if (dto.email().length() > 50 || dto.nickname().length() > 20) {
            // 적절한 에러 코드가 없다면 BAD_REQUEST(COMMON400) 사용
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

        // 이메일 형식 체크
        if (!Pattern.matches(EMAIL_REGEX, dto.email())) {
            throw new GeneralException(GeneralErrorCode.INVALID_EMAIL_FORMAT);
        }

        // 비밀번호 형식 체크
        if (!Pattern.matches(PASSWORD_REGEX, dto.password())) {
            throw new GeneralException(GeneralErrorCode.INVALID_PASSWORD_FORMAT);
        }
    }

    // 헬퍼 메서드: null이거나, 빈 문자열("")이거나, 공백(" ")인 경우 true
    private boolean isInvalid(String value) {
        return value == null || value.isBlank();
    }


    public UserResDTO.LoginRes Login(UserReqDTO.LoginReq dto) {
        // 1. 이메일로 유저 찾기
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), user.getPasswordHash())) {
            throw new GeneralException(GeneralErrorCode.PASSWORD_MISMATCH);
        }

        // 3. 토큰 발급
        String accessToken = jwtUtil.createAccessToken(user.getEmail(), user.getNickname(), "ROLE_USER");

        // 4. 응답 반환
        return UserResDTO.LoginRes.builder()
                .memberId(user.getId())
                .accessToken(accessToken)
                .nickname(user.getNickname())
                .build();
    }
}
