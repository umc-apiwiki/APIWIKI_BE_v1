package com.umc.apiwiki.domain.wiki.service.command;

import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.domain.user.repository.UserRepository;
import com.umc.apiwiki.domain.wiki.dto.WikiReqDTO;
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
@Transactional
public class WikiCommandService {

    private static final int MAX_CONTENT_LENGTH = 50000;
    private final WikiRepository wikiRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public WikiResDTO.Content returnWiki(Long apiId) {
        Wiki wiki = wikiRepository.findByApiId(apiId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.WIKI_NOT_FOUND));

        return WikiResDTO.Content.builder()
                .content(wiki.getContentMd())
                .version(wiki.getVersion())
                .build();
    }

    public String editingWiki(Long userId, Long apiId, WikiReqDTO.EditContent dto) {
        // 1. 유효성 검사
        // dto.content의 내용 누락 or 용량 초과
        if (dto.content() == null || dto.content().isBlank() || dto.content().length() > MAX_CONTENT_LENGTH) {
            throw new GeneralException(GeneralErrorCode.INVALID_WIKI_FORMAT);
        }

        // 2. 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        // 3. 위키 조회
        Wiki wiki = wikiRepository.findByApiId(apiId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.WIKI_NOT_FOUND));

        // 4. 버전 검증 (낙관적 락 로직)
        if (!wiki.getVersion().equals(dto.version())) {
            throw new GeneralException(GeneralErrorCode.VERSION_ERROR);
        }

        // 5. 내용 반영
        wiki.updateContent(dto.content(), user);

        return "위키 수정 완료";
    }
}
