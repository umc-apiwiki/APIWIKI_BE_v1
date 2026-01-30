package com.umc.apiwiki.domain.api.controller;

import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ApiControllerDocs {

    @Operation(
            summary = "API 상세 조회 By 제인",
            description = "API 개요 탭에서 한줄 설명, 카테고리 태그, 긴 설명 등을 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "API 상세 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "요청한 API를 찾을 수 없습니다. (API4001)")
    })
    @GetMapping("/apis/{apiId}")
    ApiResponse<ApiDTO.ApiDetail> getApiDetail(
            @PathVariable Long apiId
    );
}
