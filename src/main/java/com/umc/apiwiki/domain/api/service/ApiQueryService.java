package com.umc.apiwiki.domain.api.service;

import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.entity.Category;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ApiQueryService {

    @PersistenceContext
    private EntityManager em;

    public ApiDTO.ApiDetail getApiDetail(Long apiId) {

        Api api = em.find(Api.class, apiId);
        if (api == null) {
            // API4001
            throw new GeneralException(GeneralErrorCode.API_NOT_FOUND);
        }

        List<Category> categories = em.createQuery("""
                select c
                from ApiCategoriesMap m
                join m.category c
                where m.api.id = :apiId
                """, Category.class)
                .setParameter("apiId", apiId)
                .getResultList();

        return ApiDTO.ApiDetail.from(api, categories);
    }
}