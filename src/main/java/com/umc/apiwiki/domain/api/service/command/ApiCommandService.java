package com.umc.apiwiki.domain.api.service.command;

import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.repository.ApiRepository;
import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.domain.user.entity.UserFavoriteApi;
import com.umc.apiwiki.domain.user.repository.UserFavoriteApiRepository;
import com.umc.apiwiki.domain.user.repository.UserRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiCommandService {

    private final UserFavoriteApiRepository favoriteRepository;
    private final ApiRepository apiRepository;
    private final UserRepository userRepository;

    public ApiResDTO.FavoriteToggle toggleFavorite(Long userId, Long apiId) {

        // 1. 대상 API 존재 확인
        Api api = apiRepository.findById(apiId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.API_NOT_FOUND));

        // 2. 유저 존재 확인 (SecurityContext에 있어도 DB 정합성을 위해 조회 추천)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        // 3. 좋아요 기록 조회
        Optional<UserFavoriteApi> existingFavorite = favoriteRepository.findByUserIdAndApiId(userId, apiId);

        boolean isFavorited; // 최종 상태

        if (existingFavorite.isPresent()) {
            // [삭제] 이미 좋아요가 존재함
            favoriteRepository.delete(existingFavorite.get());
            isFavorited = false;
        } else {
            // [생성] 좋아요가 존재하지 않음
            UserFavoriteApi newFavorite = UserFavoriteApi.builder()
                    .user(user)
                    .api(api)
                    .build();
            favoriteRepository.save(newFavorite);
            isFavorited = true;
        }

        return new ApiResDTO.FavoriteToggle(apiId, isFavorited);
    }
}