package com.umc.apiwiki.domain.community.service.command;

import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.repository.ApiRepository;
import com.umc.apiwiki.domain.api.service.command.ApiCommandService;
import com.umc.apiwiki.domain.community.dto.review.ApiReviewReqDTO;
import com.umc.apiwiki.domain.community.dto.review.ApiReviewResDTO;
import com.umc.apiwiki.domain.community.entity.review.ApiReview;
import com.umc.apiwiki.domain.community.repository.review.ApiReviewRepository;
import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.domain.user.repository.UserRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiReviewCommandService {

    private final ApiReviewRepository apiReviewRepository;
    private final ApiRepository apiRepository;
    private final UserRepository userRepository;
    private final ApiCommandService apiCommandService;

    public ApiReviewResDTO.Create createReview(Long apiId, Long userId, ApiReviewReqDTO.Create dto) {
        // 1. API 존재 여부 확인
        Api api = apiRepository.findById(apiId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.API_NOT_FOUND));

        // 2. 유저 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        // 3. Entity 생성 및 저장
        ApiReview newReview = ApiReview.builder()
                .api(api)
                .user(user)
                .rating(dto.rating())
                .comment(dto.comment())
                .build();

        ApiReview savedReview = apiReviewRepository.save(newReview);

        // 4. 평균 별점 갱신
        apiCommandService.updateAvgRating(apiId);


        // 5. 갱신된 평균 별점 다시 조회
        BigDecimal avgRating = apiRepository.findById(apiId)
                .map(Api::getAvgRating)
                .orElse(null);

        // 6. 응답 반환
        return ApiReviewResDTO.Create.builder()
                .reviewId(savedReview.getId())
                .avgRating(avgRating)
                .build();
    }

    public ApiReviewResDTO.Create deleteReview(Long apiId, Long reviewId, Long userId) {
        // 1. 리뷰 조회
        ApiReview review = apiReviewRepository.findById(reviewId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2. 본인 리뷰 검증
        if (!review.getUser().getId().equals(userId)) {
            throw new GeneralException(GeneralErrorCode.FORBIDDEN);
        }

        // 3. API 일치 검증
        if (!review.getApi().getId().equals(apiId)) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

        // 4. 리뷰 삭제
        apiReviewRepository.delete(review);

        // 5. 평균 별점 갱신
        apiCommandService.updateAvgRating(apiId);

        // 6. 갱신된 평균 별점 조회
        BigDecimal avgRating = apiRepository.findById(apiId)
                .map(Api::getAvgRating)
                .orElse(null);

        return ApiReviewResDTO.Create.builder()
                .reviewId(reviewId)
                .avgRating(avgRating)
                .build();
    }
}
