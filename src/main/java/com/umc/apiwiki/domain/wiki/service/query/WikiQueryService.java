package com.umc.apiwiki.domain.wiki.service.query;

import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.domain.wiki.dto.WikiResDTO;
import com.umc.apiwiki.domain.wiki.entity.Wiki;
import com.umc.apiwiki.domain.wiki.entity.WikiEditRequest;
import com.umc.apiwiki.domain.wiki.repository.WikiEditRequestRepository;
import com.umc.apiwiki.domain.wiki.repository.WikiRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WikiQueryService {
    private final WikiRepository wikiRepository;
    private final WikiEditRequestRepository wikiEditRequestRepository;

    public WikiResDTO.Content returnWiki(Long apiId) {
        Wiki wiki = wikiRepository.findByApiId(apiId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.WIKI_NOT_FOUND));

        return WikiResDTO.Content.builder()
                .content(wiki.getContentMd())
                .version(wiki.getVersion())
                .build();
    }

    public Page<UserResDTO.MyWikiHistory> returnMyWikiHistory(int page, Integer size, Long userId) {
        // 1. Pageable 객체 생성
        // 정렬: 생성일 기준 내림차순(DESC)
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        // 2. Repository 조회
        Page<WikiEditRequest> requestPage = wikiEditRequestRepository.findAllByUserId(userId, pageable);

        // 3. Entity Page -> DTO Page 변환
        // .map()을 쓰면 내부의 내용물만 쏙쏙 바꿔줍니다.
        return requestPage.map(UserResDTO.MyWikiHistory::from);
    }
}
