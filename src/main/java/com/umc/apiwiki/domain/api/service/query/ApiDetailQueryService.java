package com.umc.apiwiki.domain.api.service.query;

import com.umc.apiwiki.domain.api.dto.ApiDTO;
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

    public ApiDTO.ApiDetail getApiDetail(Long apiId, Long userId) {

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

        List<ApiDTO.CategoryItem> categoryItems = categories.stream()
                .map(c -> new ApiDTO.CategoryItem(
                        c.getId(),
                        c.getName()
                ))
                .toList();

        return new ApiDTO.ApiDetail(
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
}
