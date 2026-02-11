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

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private User getUserByEmail(String email) {
        if (email == null) {
            throw new GeneralException(GeneralErrorCode.LOGIN_REQUIRED);
        }

        return userRepository.findByEmail(email)
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

        if (req.nickname() != null) {
            if (userRepository.existsByNicknameAndIdNot(req.nickname(), user.getId())) {
                throw new GeneralException(GeneralErrorCode.DUPLICATE_NICKNAME);
            }
            user.changeNickname(req.nickname());
            isUpdated = true;
        }

        if (req.password() != null) {
            if (!req.password().equals(req.passwordConfirm())) {
                throw new GeneralException(GeneralErrorCode.PASSWORD_MISMATCH);
            }
            user.changePassword(passwordEncoder.encode(req.password()));
            isUpdated = true;
        }

        if (!isUpdated) {
            throw new GeneralException(GeneralErrorCode.INVALID_PROFILE_UPDATE);
        }
    }

    // 회원 탈퇴 (하드 딜리트)
    public void deleteUser(String email) {
        User user = getUserByEmail(email);
        userRepository.delete(user);
    }
}
