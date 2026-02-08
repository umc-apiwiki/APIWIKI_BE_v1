package com.umc.apiwiki.domain.community.dto.review;

import lombok.Builder;

import java.math.BigDecimal;

public class ApiReviewResDTO {

    @Builder
    public record Create(
            Long reviewId,
            BigDecimal avgRating
    ) {}
}
