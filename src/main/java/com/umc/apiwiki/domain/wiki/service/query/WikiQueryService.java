package com.umc.apiwiki.domain.wiki.service.query;

import com.umc.apiwiki.domain.wiki.dto.WikiResDTO;
import com.umc.apiwiki.domain.wiki.entity.Wiki;
import com.umc.apiwiki.domain.wiki.repository.WikiRepository;
import com.umc.apiwiki.global.apiPayload.code.GeneralErrorCode;
import com.umc.apiwiki.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WikiQueryService {
    private final WikiRepository wikiRepository;

    public WikiResDTO.Content returnWiki(Long apiId) {
        Wiki wiki = wikiRepository.findByApiId(apiId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.WIKI_NOT_FOUND));

        return WikiResDTO.Content.builder()
                .content(wiki.getContentMd())
                .version(wiki.getVersion())
                .build();
    }
}
