package com.umc.apiwiki.domain.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderCompany {

    KAKAO("카카오"),
    NAVER("네이버"),
    GOOGLE("구글"),
    OPEN_DATA("공공데이터포털"),
    ETC("기타");

    private final String description;
}