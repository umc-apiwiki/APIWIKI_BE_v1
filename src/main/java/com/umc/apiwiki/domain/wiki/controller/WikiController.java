package com.umc.apiwiki.domain.wiki.controller;

import com.umc.apiwiki.domain.wiki.dto.WikiReqDTO;
import com.umc.apiwiki.domain.wiki.dto.WikiResDTO;
import com.umc.apiwiki.domain.wiki.service.command.WikiCommandService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WikiController implements WikiControllerDocs{

    private final WikiCommandService wikiCommandService;

    @GetMapping("/apis/{apiId}/wiki")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ApiResponse<WikiResDTO.Content> returnWiki(
            @PathVariable Long apiId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, wikiCommandService.returnWiki(apiId));
    }

    @PatchMapping("/apis/{apiId}/wiki")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ApiResponse<String> editingWiki(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long apiId,
            @RequestBody @Valid WikiReqDTO.EditContent dto) {

        Long userId = userDetails.getUser().getId();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, wikiCommandService.editingWiki(userId, apiId, dto));
    }
}
