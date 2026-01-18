package com.umc.apiwiki.domain.user.dto;

public class UserReqDTO {
    public record Signup(
            String email,
            String password,
            String nickname
    ) {}

}
