package com.umc.apiwiki.domain.user.service.query;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.api.entity.QApi;
import com.umc.apiwiki.domain.community.entity.review.QApiReview;
import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.domain.user.entity.QUserFavoriteApi;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserActivityQueryService {

    private final JPAQueryFactory queryFactory; // QueryDSL 사용

    public List<UserResDTO.DailyActivityRes> getMyActivities() {
        // 1. 현재 로그인한 유저 ID 확보
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new GeneralException(GeneralErrorCode.UNAUTHORIZED);
        }
        Long userId = userDetails.getUser().getId();

        // 2. QClass 정의
        QUserFavoriteApi favorite = QUserFavoriteApi.userFavoriteApi;
        QApi api = QApi.api; // favorite.api로 접근해도 되지만 명시적 조인을 위해 선언
        QApiReview review = QApiReview.apiReview;

        // 3. QueryDSL 조회 (API 상세 정보 + 북마크 생성일자)
        // ApiSearchQueryService의 로직을 참고하여 필요한 필드를 모두 가져옵니다.
        List<Tuple> results = queryFactory
                .select(
                        Projections.constructor(
                                ApiResDTO.ApiPreview.class, // 기존 DTO 재활용
                                api.id,
                                api.name,
                                api.summary,
                                api.avgRating.coalesce(BigDecimal.ZERO),
                                review.id.count(), // 리뷰 수
                                api.viewCounts.coalesce(0L),
                                api.pricingType,
                                api.authType,
                                api.providerCompany,
                                Expressions.asBoolean(true) // 내 활동 내역이므로 무조건 true
                        ),
                        favorite.createdAt // 그룹화를 위해 날짜 정보 필요
                )
                .from(favorite)
                .join(favorite.api, api) // N+1 방지: Inner Join
                .leftJoin(review).on(review.api.id.eq(api.id)) // 리뷰 수 계산용 Left Join
                .where(favorite.user.id.eq(userId))
                .groupBy(favorite.id, api.id) // 집계 함수(count) 사용을 위해 그룹핑
                .orderBy(favorite.createdAt.desc()) // 최신순 정렬
                .fetch();

        // 4. 조회된 데이터를 날짜별로 그룹화 (Java Stream)
        Map<LocalDate, List<ApiResDTO.ApiPreview>> groupedMap = results.stream()
                .collect(Collectors.groupingBy(
                        tuple -> tuple.get(favorite.createdAt).toLocalDate(), // Key: 날짜
                        LinkedHashMap::new, // 순서 보장을 위해 LinkedHashMap 사용 (이미 쿼리에서 정렬했으므로)
                        Collectors.mapping(
                                tuple -> tuple.get(0, ApiResDTO.ApiPreview.class), // Value: API 정보
                                Collectors.toList()
                        )
                ));

        // 5. 응답 DTO 변환
        List<UserResDTO.DailyActivityRes> timeline = groupedMap.entrySet().stream()
                .map(entry -> UserResDTO.DailyActivityRes.builder()
                        .date(entry.getKey())
                        .count(entry.getValue().size())
                        .activities(entry.getValue())
                        .build())
                .toList();

        return groupedMap.entrySet().stream()
                .map(entry -> UserResDTO.DailyActivityRes.builder()
                        .date(entry.getKey())
                        .count(entry.getValue().size())
                        .activities(entry.getValue())
                        .build())
                .toList();
        }
}
