package com.umc.apiwiki.domain.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<ApiRepository, Long> , ApiQueryDsl {
}
