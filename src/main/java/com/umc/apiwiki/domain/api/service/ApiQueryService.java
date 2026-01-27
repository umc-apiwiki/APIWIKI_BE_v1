package com.umc.apiwiki.domain.api.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.domain.api.entity.QApi;
import com.umc.apiwiki.domain.api.entity.QApiCategoriesMap;
import com.umc.apiwiki.domain.api.enums.AuthType;
import com.umc.apiwiki.domain.api.enums.PricingType;
import com.umc.apiwiki.domain.api.enums.ProviderCompany;
import com.umc.apiwiki.domain.community.entity.review.QApiReview;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiQueryService {

    private final EntityManager entityManager;

    public Page<ApiDTO.ApiPreview> searchApis(
            int page,
            Integer size,
            Long categoryId,
//            String q,
//            String sort,
            ProviderCompany providerCompany,
            AuthType authType,
            PricingType pricingType,
            BigDecimal minRating
    ) {

        QApi api = QApi.api;
        QApiReview review = QApiReview.apiReview;
        QApiCategoriesMap map = QApiCategoriesMap.apiCategoriesMap;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();

        // 필터 조건
        if (providerCompany != null) builder.and(api.providerCompany.eq(providerCompany));
        if (authType != null) builder.and(api.authType.eq(authType));
        if (pricingType != null) builder.and(api.pricingType.eq(pricingType));
        if (minRating != null) builder.and(api.avgRating.goe(minRating));

        // 카테고리 필터 (exists 서브쿼리)
        if (categoryId != null) {
            builder.and(
                    JPAExpressions.selectOne()
                            .from(map)
                            .where(
                                    map.api.id.eq(api.id)
                                            .and(map.category.id.eq(categoryId))
                            )
                            .exists()
            );
        }

        // 검색 조건
//        if (q != null && !q.isBlank()) {
//            builder.and(
//                    api.name.containsIgnoreCase(q)
//                            .or(api.summary.containsIgnoreCase(q))
//                            .or(api.longDescription.containsIgnoreCase(q))
//            );
//        }

        int pageSize = (size != null) ? size : 16;
        Pageable pageable = PageRequest.of(page, pageSize);

        // reviewCount 서브쿼리
        var reviewCountSubQuery =
                JPAExpressions.select(review.count())
                        .from(review)
                        .where(review.api.id.eq(api.id));

        // 정렬 옵션
//        if (sort == null || sort.isBlank()) {
//            sort = "latest";
//        }

//        var orderSpecifier = switch (sort) {
//            case "popular" -> api.viewCounts.desc();
//            case "mostReviewed" -> reviewCountSubQuery.desc();
//            case "latest" -> api.createdAt.desc();
//            default -> api.createdAt.desc(); // 잘못된 값 방어
//        };

        // 목록 조회
        List<ApiDTO.ApiPreview> content = queryFactory
                .select(Projections.constructor(
                        ApiDTO.ApiPreview.class,
                        api.id,
                        api.name,
                        api.summary,
                        api.avgRating,
                        reviewCountSubQuery,
                        api.viewCounts,
                        api.pricingType,
                        api.authType,
                        api.providerCompany
                ))
                .from(api)
                .where(builder)
                .orderBy(api.createdAt.desc())  // 정렬 적용
//                .orderBy(orderSpecifier)   // 기존 createdAt.desc() → 동적 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // total count
        Long total = queryFactory
                .select(api.count())
                .from(api)
                .where(builder)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        // null 안전처리
        content = content.stream()
                .map(p -> new ApiDTO.ApiPreview(
                        p.apiId(),
                        p.name(),
                        p.summary(),
                        p.avgRating() != null ? p.avgRating() : BigDecimal.ZERO,
                        p.reviewCount() != null ? p.reviewCount() : 0L,
                        p.viewCounts() != null ? p.viewCounts() : 0L,
                        p.pricingType(),
                        p.authType(),
                        p.providerCompany()
                ))
                .toList();

        return new PageImpl<>(content, pageable, totalCount);
    }
}