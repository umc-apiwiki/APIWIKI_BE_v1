package com.umc.apiwiki.domain.community.repository.review;

import com.umc.apiwiki.domain.community.entity.review.ApiReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiReviewRepository extends JpaRepository<ApiReview, Long> {

}
