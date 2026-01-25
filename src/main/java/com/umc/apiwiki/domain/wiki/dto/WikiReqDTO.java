package com.umc.apiwiki.domain.wiki.dto;

import lombok.Getter;

public class WikiReqDTO {

    public record EditContent(
            String content,
            Long version
    ) {}
}
