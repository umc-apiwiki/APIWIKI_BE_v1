package com.umc.apiwiki.domain.wiki.controller;

import com.umc.apiwiki.domain.wiki.dto.WikiReqDTO;
import com.umc.apiwiki.domain.wiki.dto.WikiResDTO;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface WikiControllerDocs {
    @Operation(
            summary = "위키 수정 요청 API By 이노",
            description = "위키 수정 버튼을 눌렀을 때 해당 위키의 내용과 버전을 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/apis/{apiId}/wiki")
    ApiResponse<WikiResDTO.Content> returnWiki(
            @PathVariable Long apiId
    );

    @Operation(
            summary = "위키 수정 API By 이노",
            description = """
    위키 수정 완료 버튼을 눌렀을 때 해당 위키의 내용을 수정합니다.
     단, 해당 위키가 이미 수정이 이루어진 위키라면 수정을 중단하고 에러 메시지를 전송합니다.
    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PatchMapping("/apis/{apiId}/wiki")
    ApiResponse<String> editingWiki(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long apiId,
            @RequestBody @Valid WikiReqDTO.EditContent dto
    );
}
