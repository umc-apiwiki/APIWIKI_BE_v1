package com.umc.apiwiki.domain.api.service.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.api.entity.QApi;
import com.umc.apiwiki.domain.api.entity.QApiCategoriesMap;
import com.umc.apiwiki.domain.api.enums.*;
import com.umc.apiwiki.domain.community.entity.review.QApiReview;
import com.umc.apiwiki.domain.user.entity.QUserFavoriteApi;
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

    public Page<ApiResDTO.ApiPreview> searchApis(
            Long userId,
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
        QUserFavoriteApi favorite = QUserFavoriteApi.userFavoriteApi;

        BooleanBuilder builder = new BooleanBuilder();

        // 필터 로직
        if (providerCompany != null) builder.and(api.providerCompany.eq(providerCompany));
        if (authType != null) builder.and(api.authType.eq(authType));
        if (pricingType != null) builder.and(api.pricingType.eq(pricingType));
        if (minRating != null) builder.and(api.avgRating.goe(minRating));

        // 검색 로직
        if (q != null && !q.isBlank()) {
            String keyword = "%" + q.toLowerCase() + "%";
            builder.and(
                    api.name.lower().like(keyword)
                            .or(api.summary.lower().like(keyword))
                            .or(Expressions.stringTemplate("lower({0})", api.providerCompany.stringValue()).like(keyword))
                            .or(Expressions.stringTemplate("lower({0})", api.authType.stringValue()).like(keyword))
                            .or(Expressions.stringTemplate("lower({0})", api.pricingType.stringValue()).like(keyword))
            );
        }

        int pageSize = (size != null) ? size : 16;
        Pageable pageable = PageRequest.of(page, pageSize);

        // 1. 쿼리 기본 구조 생성
        var query = queryFactory
                .select(Projections.constructor(
                        ApiResDTO.ApiPreview.class,
                        api.id,
                        api.name,
                        api.summary,
                        api.logo,
                        api.avgRating.coalesce(BigDecimal.ZERO),
                        review.id.count(),
                        api.viewCounts.coalesce(0L),
                        api.pricingType,
                        api.authType,
                        api.providerCompany,
                        // userId가 있으면 favorite 테이블 체크, 없으면 무조건 false
                        userId != null ? favorite.id.isNotNull() : Expressions.asBoolean(false)
                ))
                .from(api)
                .leftJoin(review).on(review.api.id.eq(api.id));

        // 2. userId가 있을 때만 좋아요 테이블 조인
        if (userId != null) {
            query.leftJoin(favorite).on(favorite.api.id.eq(api.id).and(favorite.user.id.eq(userId)));
        }

        // 카테고리 필터
        if (categoryId != null) {
            query.join(map)
                    .on(map.api.id.eq(api.id))
                    .where(map.category.id.eq(categoryId));
        }

        // 정렬 처리
        boolean desc = direction == SortDirection.DESC;
        OrderSpecifier<?> order = switch (sort) {
            case POPULAR -> desc ? api.viewCounts.desc() : api.viewCounts.asc();
            case MOST_REVIEWED -> desc ? review.id.count().desc() : review.id.count().asc();
            case LATEST -> desc ? api.createdAt.desc() : api.createdAt.asc();
        };

        // 3. 쿼리 실행 (GroupBy도 동적으로 처리하거나, favorite.id가 없어도 되게 처리)
        query.where(builder)
                .groupBy(api.id); // 기본적으로 api.id로 그룹핑

        // 로그인 상태라면 favorite.id도 그룹핑에 추가 (SQL 모드 에러 방지)
        if (userId != null) {
            query.groupBy(favorite.id);
        }

        List<ApiResDTO.ApiPreview> content = query
                .orderBy(order)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(api.count())
                .from(api)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}

