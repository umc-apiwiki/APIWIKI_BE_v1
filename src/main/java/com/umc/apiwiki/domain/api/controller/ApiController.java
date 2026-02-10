package com.umc.apiwiki.domain.api.controller;

import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.api.enums.*;
import com.umc.apiwiki.domain.api.service.command.ApiCommandService;
import com.umc.apiwiki.domain.api.service.query.ApiDetailQueryService;
import com.umc.apiwiki.domain.api.service.query.ApiSearchQueryService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apis")
public class ApiController implements ApiControllerDocs{

    private final ApiDetailQueryService apiDetailQueryService;
    private final ApiSearchQueryService apiSearchQueryService;
    private final ApiCommandService apiCommandService;

    @GetMapping("/{apiId}")
    @Override
    public ApiResponse<ApiResDTO.ApiDetail> getApiDetail(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long apiId
    ) {
        Long userId = (userDetails != null) ? userDetails.getUser().getId() : null;

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                apiDetailQueryService.getApiDetail(apiId, userId)
        );
    }

    @GetMapping("")
    @Override
    public ApiResponse<PageResponseDTO<ApiResDTO.ApiPreview>> searchApis(
            @AuthenticationPrincipal CustomUserDetails userDetails,

            // page는 0-based 로 명시(Pageable 기준과 일치)
            // 음수 방지
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,

            // size는 null 허용하지만 값이 있으면 양수만 허용
            @RequestParam(required = false, defaultValue = "16") @Positive Integer size,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String q, // 검색어
            @RequestParam(required = false, defaultValue = "LATEST") ApiSortType sort, // 정렬 옵션
            @RequestParam(defaultValue = "ASC") SortDirection direction, // 정렬 방향
            @RequestParam(required = false, name = "providers") ProviderCompany providerCompany,
            @RequestParam(required = false, name = "authTypes") AuthType authType,
            @RequestParam(required = false, name = "pricingTypes") PricingType pricingType,
            @RequestParam(required = false) @Positive @DecimalMax("5.0") BigDecimal minRating
    ) {
        Long userId = (userDetails != null) ? userDetails.getUser().getId() : null;

        Page<ApiResDTO.ApiPreview> resultPage = apiSearchQueryService.searchApis(
                userId,
                page,
                size,
                categoryId,
                q,
                sort,
                direction,
                providerCompany,
                authType,
                pricingType,
                minRating
        );

        return ApiResponse.onPageSuccess(GeneralSuccessCode.OK, resultPage);
    }

    @PostMapping("/{apiId}/favorite")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ApiResponse<ApiResDTO.FavoriteToggle> toggleFavorite(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long apiId
    ) {
        Long userId = userDetails.getUser().getId();

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                apiCommandService.toggleFavorite(userId, apiId)
        );
    }

    @GetMapping("/{apiId}/pricing")
    @Override
    public ApiResponse<ApiResDTO.ApiPricing> getApiPricing(
            @PathVariable Long apiId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                apiDetailQueryService.getApiPricing(apiId)
        );
    }

    // 비슷한 API 반환
    @GetMapping("/{apiId}/similar")
    @Override
    public ApiResponse<List<ApiResDTO.ApiSimilarPreview>> getSimilarApis(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long apiId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                apiDetailQueryService.getSimilarApis(apiId)
        );
    }
}