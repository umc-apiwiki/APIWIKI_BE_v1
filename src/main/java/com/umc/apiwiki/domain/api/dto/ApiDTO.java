package com.umc.apiwiki.domain.api.dto;

import com.umc.apiwiki.domain.api.enums.AuthType;
import com.umc.apiwiki.domain.api.enums.PricingType;
import com.umc.apiwiki.domain.api.enums.ProviderCompany;

import java.math.BigDecimal;

public class ApiDTO {
    // API 목록 미리보기 DTO(Explore / 필터용)
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
    ) { }
}
