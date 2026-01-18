package com.umc.apiwiki.domain.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthType {

    OAUTH2("OAuth 2.0"),
    REFRESH_TOKEN("Refresh Token"),
    ACCESS_TOKEN("Access Token"),
    API_KEY("API Key 인증"),
    JWT("JWT"),
    COOKIE("쿠키 기반 인증"),
    BASIC("기본 인증");

    private final String description;
}