package com.umc.apiwiki.domain.api.controller;

import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.api.enums.*;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Tag(name = "API 탐색", description = "Explore 페이지 API 목록 조회 + 필터 + 상세 조회")
public interface ApiControllerDocs {

    // API 목록 조회, 필터링
    @Operation(
            summary = "API 목록 조회 (Explore + 필터) By 악어",
            description = """
                    Explore 페이지용 API 목록 조회입니다.
                    
                    ▪ page는 0-based 입니다.
                    ▪ 기본 size = 16
                    ▪ 모든 필터는 조합 가능합니다.
                    
                    [정렬 옵션]
                    - latest : 최신 등록순 (기본값)
                    - popular : 조회수 순
                    - mostReviewed : 리뷰 많은 순
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "API 상세 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "요청한 API를 찾을 수 없습니다. (API4001)")
    })
    @GetMapping("/apis")
    ApiResponse<PageResponseDTO<ApiResDTO.ApiPreview>> searchApis(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PositiveOrZero int page,
            @Positive Integer size,
            Long categoryId,
            String q,
            ApiSortType sort,
            SortDirection direction,
            ProviderCompany providerCompany,
            AuthType authType,
            PricingType pricingType,
            @Positive
            @DecimalMax("5.0")
            BigDecimal minRating
    );

    // API 상세 조회
    @Operation(
            summary = "API 상세 조회 By 제인",
            description = "API 개요 탭에서 한줄 설명, 카테고리 태그, 긴 설명 등을 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "API 상세 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "요청한 API를 찾을 수 없습니다. (API4001)")
    })
    @GetMapping("/apis/{apiId}")
    ApiResponse<ApiResDTO.ApiDetail> getApiDetail(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long apiId
    );

    @Operation(
            summary = "API 좋아요 By 이노",
            description = """
                    해당 API를 좋아요(북마크) 합니다.
                    이미 좋아요된 상태에서 한 번 더 호출하면 기존의 좋아요를 취소할 수 있습니다.
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "API 상세 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "요청한 API를 찾을 수 없습니다. (API4001)")
    })
    @PostMapping("/{apiId}/favorite")
    @PreAuthorize("isAuthenticated()")
    ApiResponse<ApiResDTO.FavoriteToggle> toggleFavorite(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long apiId
    );

    @Operation(
            summary = "API 비용 정보 조회 By 제인",
            description = """
                API 상세 - 비용정보 탭에서 사용할 데이터를 조회합니다.
                
                ▪ pricingInfoCsv는 DB에 저장된 CSV 텍스트를 반환합니다.
                ▪ pricingType은 무료/혼합/유료 필터링 및 뱃지 용도입니다.
                """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "API 비용 정보 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "요청한 API를 찾을 수 없습니다. (API4001)")
    })
    @GetMapping("/apis/{apiId}/pricing")
    ApiResponse<ApiResDTO.ApiPricing> getApiPricing(
            @PathVariable Long apiId
    );
}