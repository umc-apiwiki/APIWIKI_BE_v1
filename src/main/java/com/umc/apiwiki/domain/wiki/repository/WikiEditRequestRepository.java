package com.umc.apiwiki.domain.wiki.repository;

import com.umc.apiwiki.domain.wiki.entity.WikiEditRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WikiEditRequestRepository extends JpaRepository<WikiEditRequest, Long> {
    @Query(value = "SELECT r FROM WikiEditRequest r " +
            "JOIN FETCH r.wiki w " +
            "JOIN FETCH w.api a " +
            "WHERE r.user.id = :userId",
            countQuery = "SELECT count(r) FROM WikiEditRequest r WHERE r.user.id = :userId")
    Page<WikiEditRequest> findAllByUserId(@Param("userId") Long userId, Pageable pageable);}
