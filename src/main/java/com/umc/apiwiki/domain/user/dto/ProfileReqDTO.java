package com.umc.apiwiki.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfileReqDTO {
    // 프로필 수정
    public record Update(
            @NotBlank String nickname,
            @NotBlank String password,
            @NotBlank String passwordConfirm
    ) {}
}
