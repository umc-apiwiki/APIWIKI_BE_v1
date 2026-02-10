package com.umc.apiwiki.domain.community.service.query;

import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.repository.ApiRepository;
import com.umc.apiwiki.domain.community.dto.review.ApiReviewResDTO;
import com.umc.apiwiki.domain.community.entity.review.ApiReview;
import com.umc.apiwiki.domain.community.repository.review.ApiReviewRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiReviewQueryService {

    private final ApiReviewRepository apiReviewRepository;
    private final ApiRepository apiRepository;

    public ApiReviewResDTO.ReviewList getApiReviews(Long apiId, int page) {

        // API 존재 여부 확인
        Api api = apiRepository.findById(apiId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.API_NOT_FOUND));

        // 페이징 설정 (최신순, 16개)
        PageRequest pageRequest = PageRequest.of(
                page,
                16,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        // 리뷰 조회 (EntityGraph로 user 같이 로딩)
        Page<ApiReview> reviewPage =
                apiReviewRepository.findByApiId(apiId, pageRequest);

        // Entity → DTO 변환
        Page<ApiReviewResDTO.ReviewItem> dtoPage =
                reviewPage.map(ApiReviewResDTO.ReviewItem::from);

        return new ApiReviewResDTO.ReviewList(
                api.getAvgRating(),
                reviewPage.getTotalElements(),
                new PageResponseDTO<>(dtoPage)
        );
    }
}