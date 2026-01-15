package com.umc.apiwiki.domain.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PricingType {

    FREE("무료"),
    PAID("유료"),
    MIXED("혼합");

    private final String description;
}