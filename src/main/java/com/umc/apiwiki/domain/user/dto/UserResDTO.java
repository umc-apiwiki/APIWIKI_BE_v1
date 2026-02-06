package com.umc.apiwiki.domain.user.dto;

import com.umc.apiwiki.domain.community.entity.review.ApiReview;
import com.umc.apiwiki.domain.user.entity.UserFavoriteApi;
import com.umc.apiwiki.domain.wiki.entity.WikiEditRequest;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class UserResDTO {

    @Builder
    public record LoginRes(
            Long memberId,
            String accessToken,
            String nickname
    ) {}

    @Builder
    public record MyProfileRes(
            String nickname
    ) {}

    @Builder
    public record MyWikiHistory(
            Long requestId,
            Long apiId,
            String apiName,
            LocalDateTime editedAt
    ){
        public static MyWikiHistory from(WikiEditRequest request) {
            return MyWikiHistory.builder()
                    .requestId(request.getId())
                    .apiId(request.getWiki().getApi().getId())
                    .apiName(request.getWiki().getApi().getName())
                    .editedAt(request.getCreatedAt())
                    .build();
        }
    }

    @Builder
    public record MyActivitiesRes(
            List<MyReviewRes> reviews,
            List<MyFavoriteRes> favorites
    ) {}

    @Builder
    public record MyReviewRes(
            Long apiId,
            String apiName,
            Float rating,
            String comment,
            LocalDateTime createdAt
    ) {
        public static MyReviewRes from(ApiReview review) {
            return MyReviewRes.builder()
                    .apiId(review.getApi().getId())
                    .apiName(review.getApi().getName())
                    .rating(review.getRating())
                    .comment(review.getComment())
                    .createdAt(review.getCreatedAt())
                    .build();
        }
    }

    @Builder
    public record MyFavoriteRes(
            Long apiId,
            String apiName
    ) {
        public static MyFavoriteRes from(UserFavoriteApi favorite) {
            return MyFavoriteRes.builder()
                    .apiId(favorite.getApi().getId())
                    .apiName(favorite.getApi().getName())
                    .build();
        }
    }
}
