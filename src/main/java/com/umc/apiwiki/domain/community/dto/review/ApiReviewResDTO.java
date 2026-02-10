package com.umc.apiwiki.domain.community.dto.review;

import com.umc.apiwiki.domain.community.entity.review.ApiReview;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ApiReviewResDTO {

    @Builder
    public record Create(
            Long reviewId,
            BigDecimal avgRating
    ) {}

    // 개별 리뷰 응답 DTO
    public static record ReviewItem(
            String nickname,
            String comment,
            Float rating,
            LocalDateTime createdAt
    ) {
        public static ReviewItem from(ApiReview review) {
            return new ReviewItem(
                    review.getUser().getNickname(),
                    review.getComment(),
                    review.getRating(),
                    review.getCreatedAt()
            );
        }
    }

    public static record ReviewList(
            BigDecimal totalRating,
            Long reviewCount,
            PageResponseDTO<ReviewItem> reviews
    ) {}
}
