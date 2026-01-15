package com.umc.apiwiki.domain.wiki.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RequestState {

    PENDING("검토중"),
    APPROVED("통과"),
    REJECTED("거절");

    private final String description;
}