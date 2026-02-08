package com.umc.apiwiki.domain.community.controller.review;

import com.umc.apiwiki.domain.community.dto.review.ApiReviewReqDTO;
import com.umc.apiwiki.domain.community.dto.review.ApiReviewResDTO;
import com.umc.apiwiki.domain.community.service.command.ApiReviewCommandService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apis/{apiId}/reviews")
public class ApiReviewController implements ApiReviewControllerDocs {

    private final ApiReviewCommandService apiReviewCommandService;

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ApiResponse<ApiReviewResDTO.Create> createReview(
            @PathVariable Long apiId,
            @RequestBody @Valid ApiReviewReqDTO.Create dto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getId();

        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                apiReviewCommandService.createReview(apiId, userId, dto)
        );
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ApiResponse<ApiReviewResDTO.Create> deleteReview(
            @PathVariable Long apiId,
            @PathVariable Long reviewId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getId();

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                apiReviewCommandService.deleteReview(apiId, reviewId, userId)
        );
    }
}