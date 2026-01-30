package com.umc.apiwiki.domain.api.service.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.domain.api.entity.QApi;
import com.umc.apiwiki.domain.api.entity.QApiCategoriesMap;
import com.umc.apiwiki.domain.api.enums.*;
import com.umc.apiwiki.domain.community.entity.review.QApiReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.dsl.Expressions;


import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiSearchQueryService {

    private final JPAQueryFactory queryFactory;

    public Page<ApiDTO.ApiPreview> searchApis(
            int page,
            Integer size,
            Long categoryId,
            String q,
            ApiSortType sort,
            SortDirection direction,
            ProviderCompany providerCompany,
            AuthType authType,
            PricingType pricingType,
            BigDecimal minRating
    ) {

        QApi api = QApi.api;
        QApiReview review = QApiReview.apiReview;
        QApiCategoriesMap map = QApiCategoriesMap.apiCategoriesMap;

        BooleanBuilder builder = new BooleanBuilder();

        // 필터
        if (providerCompany != null)
            builder.and(api.providerCompany.eq(providerCompany));

        if (authType != null)
            builder.and(api.authType.eq(authType));

        if (pricingType != null)
            builder.and(api.pricingType.eq(pricingType));

        if (minRating != null)
            builder.and(api.avgRating.goe(minRating));

        // 검색 (CLOB 제외)
        if (q != null && !q.isBlank()) {
            String keyword = "%" + q.toLowerCase() + "%";

            builder.and(
                    api.name.lower().like(keyword)
                            .or(api.summary.lower().like(keyword))
                            .or(Expressions.stringTemplate(
                                    "lower({0})",
                                    api.providerCompany.stringValue()
                            ).like(keyword))
                            .or(Expressions.stringTemplate(
                                    "lower({0})",
                                    api.authType.stringValue()
                            ).like(keyword))
                            .or(Expressions.stringTemplate(
                                    "lower({0})",
                                    api.pricingType.stringValue()
                            ).like(keyword))
            );
        }

        int pageSize = (size != null) ? size : 16;
        Pageable pageable = PageRequest.of(page, pageSize);

        var query = queryFactory
                .select(Projections.constructor(
                        ApiDTO.ApiPreview.class,
                        api.id,
                        api.name,
                        api.summary,
                        api.avgRating.coalesce(BigDecimal.ZERO),
                        review.id.count(),
                        api.viewCounts.coalesce(0L),
                        api.pricingType,
                        api.authType,
                        api.providerCompany
                ))
                .from(api)
                .leftJoin(review).on(review.api.id.eq(api.id));

        // 카테고리 필터
        if (categoryId != null) {
            query.join(map)
                    .on(map.api.id.eq(api.id))
                    .where(map.category.id.eq(categoryId));
        }

        // 정렬 처리

        boolean desc = direction == SortDirection.DESC;

        OrderSpecifier<?> order =
                switch (sort) {

                    case POPULAR ->
                            desc ? api.viewCounts.desc()
                                    : api.viewCounts.asc();

                    case MOST_REVIEWED ->
                            desc ? review.id.count().desc()
                                    : review.id.count().asc();

                    case LATEST ->
                            desc ? api.createdAt.desc()
                                    : api.createdAt.asc();
                };

        List<ApiDTO.ApiPreview> content =
                query
                        .where(builder)
                        .groupBy(api.id)
                        .orderBy(order)
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        Long total =
                queryFactory
                        .select(api.count())
                        .from(api)
                        .where(builder)
                        .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}

