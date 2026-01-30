package com.umc.apiwiki.domain.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ApiDTO {

    public static record ApiDetail(
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
    ) {
    }

    public static record CategoryItem(
            Long categoryId,
            String name
    ) {
    }
}
