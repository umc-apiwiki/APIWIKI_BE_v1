package com.umc.apiwiki.domain.community.controller.review;

import com.umc.apiwiki.domain.community.dto.review.ApiReviewReqDTO;
import com.umc.apiwiki.domain.community.dto.review.ApiReviewResDTO;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import org.springframework.web.bind.annotation.*;

public interface ApiReviewControllerDocs {

    @Operation(
            summary = "리뷰 작성 API By 제인",
            description = """
                    특정 API에 대한 리뷰를 작성합니다.<br>
                    하나의 사용자는 동일한 API에 대해 여러 개의 리뷰를 작성할 수 있습니다.<br>
                    리뷰에는 별점(rating)과 코멘트(comment)가 포함됩니다.
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "리뷰 작성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 필요"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "API 또는 사용자 없음")
    })
    @PostMapping("/apis/{apiId}/reviews")
    @PreAuthorize("isAuthenticated()")
    ApiResponse<ApiReviewResDTO.Create> createReview(
            @PathVariable Long apiId,
            @RequestBody ApiReviewReqDTO.Create dto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(
            summary = "리뷰 삭제 API By 제인",
            description = """
                    본인이 작성한 API 리뷰를 삭제합니다.<br>
                    다른 사용자의 리뷰는 삭제할 수 없습니다.
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 필요"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "삭제 권한 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "리뷰 없음")
    })
    @DeleteMapping("/apis/{apiId}/reviews/{reviewId}")
    @PreAuthorize("isAuthenticated()")
    ApiResponse<ApiReviewResDTO.Create> deleteReview(
            @PathVariable Long apiId,
            @PathVariable Long reviewId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(
            summary = "리뷰 조회 API By 제인",
            description = """
                    특정 API에 대한 리뷰 목록을 조회합니다.<br>
                    리뷰는 최신순으로 정렬되며, 16개 단위로 페이징됩니다.<br>
                    평균 별점과 전체 리뷰 개수를 함께 반환합니다.
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "API 없음")
    })
    @GetMapping("/apis/{apiId}/reviews")
    ApiResponse<ApiReviewResDTO.ReviewList> getReviews(
            @PathVariable Long apiId,
            @RequestParam(defaultValue = "0") int page
    );
}
