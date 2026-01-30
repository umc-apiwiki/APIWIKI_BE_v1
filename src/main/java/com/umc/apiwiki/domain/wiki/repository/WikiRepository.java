package com.umc.apiwiki.domain.wiki.repository;

import com.umc.apiwiki.domain.wiki.entity.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WikiRepository extends JpaRepository<Wiki, Long> {
    Optional<Wiki> findByApiId(Long apiId);
}
