package com.umc.apiwiki.domain.user.service.query;

import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    public UserResDTO.MyProfileRes getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new GeneralException(GeneralErrorCode.UNAUTHORIZED);
        }

        return UserResDTO.MyProfileRes.builder()
                .nickname(userDetails.getNickname())
                .build();
    }
}
