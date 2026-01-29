package com.umc.apiwiki.domain.wiki.repository;

import com.umc.apiwiki.domain.wiki.entity.WikiEditRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WikiEditRequestRepository extends JpaRepository<WikiEditRequest, Long> {
    @Query("SELECT r FROM WikiEditRequest r " +
            "JOIN FETCH r.wiki w " +
            "JOIN FETCH w.api a " +
            "WHERE r.user.id = :userId " +
            "ORDER BY r.createdAt DESC")
    List<WikiEditRequest> findAllByUserId(Long userId);
}
