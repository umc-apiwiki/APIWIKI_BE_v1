package com.umc.apiwiki.domain.user.dto;

import com.umc.apiwiki.domain.api.dto.ApiResDTO;
import com.umc.apiwiki.domain.wiki.entity.WikiEditRequest;
import lombok.Builder;

import java.time.LocalDate;
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
    public record DailyActivityRes(
            LocalDate date,
            int count,
            List<ApiResDTO.ApiPreview> activities
    ) {}
}
