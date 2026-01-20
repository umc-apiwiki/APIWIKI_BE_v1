package com.umc.apiwiki.global.apiPayload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDTO<T> {
    List<T> content;
    int totalPage;
    long totalElements;
    boolean isFirst;
    boolean isLast;

    public PageResponseDTO(Page<T> page) {
        this.content = page.getContent();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
    }
}
