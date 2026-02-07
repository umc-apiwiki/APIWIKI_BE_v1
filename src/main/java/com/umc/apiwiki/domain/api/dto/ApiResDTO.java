package com.umc.apiwiki.domain.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.umc.apiwiki.domain.api.enums.AuthType;
import com.umc.apiwiki.domain.api.enums.PricingType;
import com.umc.apiwiki.domain.api.enums.ProviderCompany;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ApiResDTO {

    // Explore / 목록 조회 DTO by 악어
    public record ApiPreview(
            Long apiId,
            String name,
            String summary,
            String logo,
            BigDecimal avgRating,
            Long reviewCount,
            Long viewCounts,
            PricingType pricingType,
            AuthType authType,
            ProviderCompany providerCompany,
            @JsonProperty("isFavorited")
            boolean isFavorited
    ) {}

    // 상세 조회 DTO by 제인
    public record ApiDetail(
            Long apiId,
            String name,
            String summary,
            String longDescription,
            String officialUrl,
            BigDecimal avgRating,
            Long viewCounts,
            List<CategoryItem> categories,
            String logo,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            @JsonProperty("isFavorited")
            boolean isFavorited
    ) {}

    public record CategoryItem(
            Long categoryId,
            String name
    ) {}

    // 좋아요 응답 DTO by 이노
    public record FavoriteToggle(
            Long apiId,
            boolean isFavorited
    ) {}

    // 비용 정보 응답 DTO by 제인
    public record ApiPricing(
            Long apiId,
            PricingType pricingType,
            String pricingInfoCsv
    ) {}

    // 비슷한 API 반환 DTO by 악어
    public record ApiSimilarPreview(
            Long apiId,
            String name,
            String logo,
            String summary,
            BigDecimal avgRating,
            PricingType pricingType,
            AuthType authType,
            ProviderCompany providerCompany,
            boolean isFavorited
    ) {}
}
