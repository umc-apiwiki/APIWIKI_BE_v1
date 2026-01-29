package com.umc.apiwiki.domain.wiki.repository;

import com.umc.apiwiki.domain.wiki.entity.WikiEditRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiEditRequestRepository extends JpaRepository<WikiEditRequest, Long> {
}
