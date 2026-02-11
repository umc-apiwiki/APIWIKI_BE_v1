package com.umc.apiwiki.domain.user.dto;

public class ProfileReqDTO {
    // 닉네임 중복 확인
    public record NicknameCheck(
            String nickname
    ) {}

    // 프로필 수정
    public record Update(
            String nickname,
            String password,
            String passwordConfirm
    ) {}
}
