package com.umc.apiwiki.domain.user.dto;

public class ProfileReqDTO {
    // 프로필 수정
    public record Update(
            String nickname,
            String password,
            String passwordConfirm
    ) {}
}
