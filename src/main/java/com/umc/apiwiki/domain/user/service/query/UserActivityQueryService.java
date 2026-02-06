package com.umc.apiwiki.domain.user.service.query;

import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.domain.user.repository.UserFavoriteApiRepository;
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
public class UserActivityQueryService {

    private final UserFavoriteApiRepository userFavoriteApiRepository;

    public UserResDTO.MyActivitiesRes getMyActivities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new GeneralException(GeneralErrorCode.UNAUTHORIZED);
        }

        Long userId = userDetails.getUser().getId();

        var favorites = userFavoriteApiRepository
                .findAllByUserId(userId)
                .stream()
                .map(UserResDTO.MyFavoriteRes::from)
                .toList();

        return UserResDTO.MyActivitiesRes.builder()
                .favorites(favorites)
                .build();
    }
}
