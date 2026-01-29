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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserResDTO.MyWikiHistory> returnMyWikiHistory(Long userId) {
        // 1. Repository에서 유저의 수정 요청 목록을 조회 (Entity)
        List<WikiEditRequest> requests = wikiEditRequestRepository.findAllByUserId(userId);

        // 2. Entity -> DTO 변환
        return requests.stream()
                .map(UserResDTO.MyWikiHistory::from)
                .collect(Collectors.toList());
    }
}
