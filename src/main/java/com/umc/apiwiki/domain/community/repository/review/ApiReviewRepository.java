package com.umc.apiwiki.domain.community.repository.review;

import com.umc.apiwiki.domain.community.entity.review.ApiReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiReviewRepository extends JpaRepository<ApiReview, Long> {

    @Query("""
            SELECT AVG(r.rating)
            FROM ApiReview r
            WHERE r.api.id = :apiId
            """)
    Double calculateAvgRating(@Param("apiId") Long apiId);

    /**
     * API별 리뷰 목록 조회 (페이징)
     * - N+1 방지를 위해 user를 EntityGraph로 함께 로딩
     */
    @EntityGraph(attributePaths = "user")
    @Query("""
            select r
            from ApiReview r
            where r.api.id = :apiId
            """)
    Page<ApiReview> findByApiId(
            @Param("apiId") Long apiId,
            Pageable pageable
    );

    /**
     * API별 리뷰 개수 조회
     * - 리뷰 개수 캐싱 전까지 사용
     */
    long countByApi_Id(Long apiId);
}
