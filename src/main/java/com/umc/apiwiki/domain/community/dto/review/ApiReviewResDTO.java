package com.umc.apiwiki.domain.community.dto.review;

import lombok.Builder;

public class ApiReviewResDTO {

    @Builder
    public record Create(
            Long reviewId
    ) {}
}
