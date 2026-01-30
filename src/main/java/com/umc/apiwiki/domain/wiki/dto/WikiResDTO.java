package com.umc.apiwiki.domain.wiki.dto;

import lombok.Builder;

public class WikiResDTO {

    @Builder
    public record Content(
            String content,
            Long version
    ) {}
}
