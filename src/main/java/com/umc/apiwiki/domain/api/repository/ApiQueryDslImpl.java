package com.umc.apiwiki.domain.api.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.api.entity.QApi;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ApiQueryDslImpl implements ApiQueryDsl {
    private final EntityManager em;

    // 검색 API
    @Override
    public List<Api> searchApi(
            Predicate predicate
    ){
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q 클래스 선언
        QApi api = QApi.api;

        return queryFactory
                .selectFrom(api)
                .where(predicate)
                .fetch();
    }
}
