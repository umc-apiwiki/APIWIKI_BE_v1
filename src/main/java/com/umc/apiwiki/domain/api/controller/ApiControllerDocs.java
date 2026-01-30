package com.umc.apiwiki.domain.api.controller;

import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.domain.api.enums.*;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Tag(name = "API 탐색", description = "Explore 페이지 API 목록 조회 및 필터")
public interface ApiControllerDocs {

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
    ApiResponse<PageResponseDTO<ApiDTO.ApiPreview>> searchApis(

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
}