package com.umc.apiwiki.domain.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryName {
    PAYMENT("결제"),
    SOCIAL_LOGIN("소셜로그인"),
    MAP("지도"),
    WEATHER("날씨"),
    AI("AI"),
    EMAIL("이메일"),
    FINANCE("금융"),
    DATA("데이터"),
    MEDIA("미디어"),
    DEV_TOOLS("개발도구"),
    CLOUD("클라우드"),
    CMS("CMS"),
    ANALYTICS("분석"),
    MONITORING("모니터링"),
    SNS("SNS"),
    COLLABORATION("협업");

    private final String name;
}