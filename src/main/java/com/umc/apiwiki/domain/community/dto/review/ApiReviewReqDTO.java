package com.umc.apiwiki.domain.community.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ApiReviewReqDTO {

    public record Create(
            @Min(0)
            @Max(5)
            Float rating,

            @NotBlank
            String comment
    ) {}
}
