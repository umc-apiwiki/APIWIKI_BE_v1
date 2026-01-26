package com.umc.apiwiki.domain.user.dto;

import lombok.Builder;

public class UserResDTO {

    @Builder
    public record LoginRes(
            Long memberId,
            String accessToken,
            String nickname
    ) {}
}
