package com.umc.apiwiki.domain.api.service.query;

import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.entity.Category;
import com.umc.apiwiki.domain.user.repository.UserFavoriteApiRepository;
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

    public ApiResDTO.ApiDetail getApiDetail(Long apiId, Long userId) {

        Api api = em.find(Api.class, apiId);
        if (api == null) {
            throw new GeneralException(GeneralErrorCode.API_NOT_FOUND);
        }

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
                isFavorited
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
}
