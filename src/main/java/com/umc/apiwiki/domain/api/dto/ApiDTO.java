package com.umc.apiwiki.domain.api.dto;

import com.umc.apiwiki.domain.api.enums.AuthType;
import com.umc.apiwiki.domain.api.enums.PricingType;
import com.umc.apiwiki.domain.api.enums.ProviderCompany;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ApiDTO {

    // Explore / 목록 조회 DTO by 악어
    public record ApiPreview(
            Long apiId,
            String name,
            String summary,
            BigDecimal avgRating,
            Long reviewCount,
            Long viewCounts,
            PricingType pricingType,
            AuthType authType,
            ProviderCompany providerCompany
    ) {}

    // 상세 조회 DTO by 재인
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
            LocalDateTime updatedAt
    ) {}

    public record CategoryItem(
            Long categoryId,
            String name
    ) {}
}
