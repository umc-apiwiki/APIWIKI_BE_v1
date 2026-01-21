package com.umc.apiwiki.domain.api.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.domain.api.entity.QApi;
import com.umc.apiwiki.domain.api.enums.AuthType;
import com.umc.apiwiki.domain.api.enums.PricingType;
import com.umc.apiwiki.domain.api.enums.ProviderCompany;
import com.umc.apiwiki.domain.api.repository.ApiRepository;
import com.umc.apiwiki.domain.community.entity.review.QApiReview;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiQueryService {

    private final ApiRepository apiRepository;
    private final EntityManager entityManager;

    // Explore / 필터용 API 목록 조회
    public Page<ApiDTO.ApiPreview> searchApis(
            int page,
            Integer size,
            ProviderCompany providerCompany,
            AuthType authType,
            PricingType pricingType,
            BigDecimal minRating
    ) {

        QApi api = QApi.api;
        QApiReview review = QApiReview.apiReview;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        BooleanBuilder builder = new BooleanBuilder();

        // 동적 쿼리: 검색 조건
        if (providerCompany != null) {
            builder.and(api.providerCompany.eq(providerCompany));
        }
        if (authType != null) {
            builder.and(api.authType.eq(authType));
        }
        if (pricingType != null) {
            builder.and(api.pricingType.eq(pricingType));
        }
        if (minRating != null) {
            builder.and(api.avgRating.goe(minRating));
        }

        int pageSize = (size != null) ? size : 16;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        // reviewCount 서브쿼리
        var reviewCountSubQuery =
                JPAExpressions.select(review.count())
                        .from(review)
                        .where(review.api.id.eq(api.id));

        // 목록 조회 + DTO 직접 생성
        List<ApiDTO.ApiPreview> content = queryFactory
                .select(Projections.constructor(
                        ApiDTO.ApiPreview.class,
                        api.id,
                        api.name,
                        api.summary,
                        api.avgRating,
                        reviewCountSubQuery,     // Long
                        api.viewCounts,          // Long (null 가능)
                        api.pricingType,
                        api.authType,
                        api.providerCompany
                ))
                .from(api)
                .where(builder)
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