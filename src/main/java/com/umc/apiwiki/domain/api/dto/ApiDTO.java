package com.umc.apiwiki.domain.api.dto;

import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.entity.Category;

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
        public static ApiDetail from(Api api, List<Category> categories) {
            return new ApiDetail(
                    api.getId(),
                    api.getName(),
                    api.getSummary(),
                    api.getLongDescription(),
                    api.getOfficialUrl(),
                    api.getAvgRating(),
                    api.getViewCounts(),
                    categories.stream().map(CategoryItem::from).toList(),
                    api.getLogo(),
                    api.getCreatedAt(),
                    api.getUpdatedAt()
            );
        }
    }

    public static record CategoryItem(
            Long categoryId,
            String name
    ) {
        public static CategoryItem from(Category category) {
            return new CategoryItem(category.getId(), category.getName().getDescription());
        }
    }
}
