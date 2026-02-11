package com.umc.apiwiki.domain.api.service.query;

import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.entity.Category;
import com.umc.apiwiki.domain.user.repository.UserFavoriteApiRepository;
import com.umc.apiwiki.domain.wiki.entity.Wiki;
import com.umc.apiwiki.domain.wiki.repository.WikiRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiDetailQueryService {

    @PersistenceContext
    private EntityManager em;
    private final UserFavoriteApiRepository favoriteRepository;
    private final WikiRepository wikiRepository;

    @Transactional
    public ApiResDTO.ApiDetail getApiDetail(Long apiId, Long userId) {

        Api api = em.find(Api.class, apiId);
        if (api == null) {
            throw new GeneralException(GeneralErrorCode.API_NOT_FOUND);
        }

        api.increaseViewCount();

        // 좋아요 여부 확인 (userId가 null이면 false)
        boolean isFavorited = userId != null && favoriteRepository.existsByUserIdAndApiId(userId, apiId);

        List<Category> categories = em.createQuery("""
                select c
                from ApiCategoriesMap m
                join m.category c
                where m.api.id = :apiId
                """, Category.class)
                .setParameter("apiId", apiId)
                .getResultList();

        List<ApiResDTO.CategoryItem> categoryItems = categories.stream()
                .map(c -> new ApiResDTO.CategoryItem(
                        c.getId(),
                        c.getName()
                ))
                .toList();

        Wiki wiki = wikiRepository.findByApiId(apiId).orElse(null);

        ApiResDTO.WikiItem wikiItem = null;
        if (wiki != null) {
            wikiItem = new ApiResDTO.WikiItem(
                    wiki.getId(),
                    wiki.getContentMd(),
                    wiki.getVersion(),
                    wiki.getUser() != null ? wiki.getUser().getNickname() : null,
                    wiki.getUpdatedAt()
            );
        }

        return new ApiResDTO.ApiDetail(
                api.getId(),
                api.getName(),
                api.getSummary(),
                api.getLongDescription(),
                api.getOfficialUrl(),
                api.getAvgRating(),
                api.getViewCounts(),
                categoryItems,
                api.getLogo(),
                api.getCreatedAt(),
                api.getUpdatedAt(),
                isFavorited,
                wikiItem
        );
    }

    // 비용 정보 탭 조회
    public ApiResDTO.ApiPricing getApiPricing(Long apiId) {
        Api api = em.find(Api.class, apiId);
        if (api == null) {
            throw new GeneralException(GeneralErrorCode.API_NOT_FOUND);
        }

        return new ApiResDTO.ApiPricing(
                api.getId(),
                api.getPricingType(),
                api.getPricingInfo()
        );
    }

    // 비슷한 API 조회 (같은 카테고리 기반, 최신순 5개)
    public List<ApiResDTO.ApiSimilarPreview> getSimilarApis(Long apiId) {

        if (em.find(Api.class, apiId) == null) {
            throw new GeneralException(GeneralErrorCode.API_NOT_FOUND);
        }

        List<Api> similarApis = em.createQuery("""
        select distinct m.api
        from ApiCategoriesMap m
        where m.category.id in (
            select m2.category.id
            from ApiCategoriesMap m2
            where m2.api.id = :apiId
        )
        and m.api.id <> :apiId
        order by m.api.createdAt desc
    """, Api.class)
                .setParameter("apiId", apiId)
                .setMaxResults(5)
                .getResultList();

        return similarApis.stream()
                .map(this::toSimilarPreview)
                .toList();
    }
    private ApiResDTO.ApiSimilarPreview toSimilarPreview(Api api) {
        return new ApiResDTO.ApiSimilarPreview(
                api.getId(),
                api.getName(),
                api.getLogo(),
                api.getAvgRating(),
                api.getPricingType(),
                api.getViewCounts()
        );
    }
}
