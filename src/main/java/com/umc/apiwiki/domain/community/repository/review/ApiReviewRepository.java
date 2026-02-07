package com.umc.apiwiki.domain.community.repository.review;

import com.umc.apiwiki.domain.community.entity.review.ApiReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiReviewRepository extends JpaRepository<ApiReview, Long> {

}
