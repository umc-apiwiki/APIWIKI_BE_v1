package com.umc.apiwiki.domain.community.repository.review;

import com.umc.apiwiki.domain.community.entity.review.ApiReview;
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
}
