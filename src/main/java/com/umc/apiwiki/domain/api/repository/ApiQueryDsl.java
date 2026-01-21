package com.umc.apiwiki.domain.api.repository;

import com.querydsl.core.types.Predicate;
import com.umc.apiwiki.domain.api.entity.Api;

import java.util.List;

public interface ApiQueryDsl {
    List<Api> searchApi(Predicate predicate);
}
