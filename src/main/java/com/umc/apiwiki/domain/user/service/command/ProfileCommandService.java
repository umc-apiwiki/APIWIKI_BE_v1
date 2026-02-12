package com.umc.apiwiki.domain.user.service.command;

import com.umc.apiwiki.domain.user.dto.ProfileReqDTO;
import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.domain.user.repository.UserRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    private User getUserByEmail(String email) {
        if (email == null) {
            throw new GeneralException(GeneralErrorCode.LOGIN_REQUIRED);
        }

        return userRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));
    }


    // 닉네임 중복 확인
    public void checkNicknameDuplicate(String nickname, String email) {
        User user = getUserByEmail(email);

        if (userRepository.existsByNicknameAndIdNot(nickname, user.getId())) {
            throw new GeneralException(GeneralErrorCode.DUPLICATE_NICKNAME);
        }
    }

    // 프로필 수정
    public void updateProfile(String email, ProfileReqDTO.Update req) {
        User user = getUserByEmail(email);

        boolean isUpdated = false;

        if (!isInvalid(req.nickname())) {

            // 닉네임 길이 제한 (회원가입과 동일: 20자)
            if (req.nickname().length() > 20) {
                throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
            }

            if (userRepository.existsByNicknameAndIdNot(req.nickname(), user.getId())) {
                throw new GeneralException(GeneralErrorCode.DUPLICATE_NICKNAME);
            }

            user.changeNickname(req.nickname());
            isUpdated = true;
        }

        if (!isInvalid(req.password())) {

            if (!req.password().equals(req.passwordConfirm())) {
                throw new GeneralException(GeneralErrorCode.PASSWORD_CONFIRM_MISMATCH);
            }

            // UserCommandService와 동일한 비밀번호 형식 검증
            if (!Pattern.matches(PASSWORD_REGEX, req.password())) {
                throw new GeneralException(GeneralErrorCode.INVALID_PASSWORD_FORMAT);
            }

            user.changePassword(passwordEncoder.encode(req.password()));
            isUpdated = true;
        }

        if (!isUpdated) {
            throw new GeneralException(GeneralErrorCode.INVALID_PROFILE_UPDATE);
        }
    }

    // 헬퍼 메서드: null이거나, 빈 문자열("")이거나, 공백(" ")인 경우 true
    private boolean isInvalid(String value) {
        return value == null || value.isBlank();
    }

    // 회원 탈퇴 (소프트 딜리트)
    public void deleteUser(String email) {
        User user = getUserByEmail(email);
        user.softDelete();
    }
}
