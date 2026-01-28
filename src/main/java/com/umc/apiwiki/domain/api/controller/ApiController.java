package com.umc.apiwiki.domain.api.controller;

import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.domain.api.service.ApiQueryService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis")
@RequiredArgsConstructor
public class ApiController {

    private final ApiQueryService apiQueryService;

    @GetMapping("/{apiId}")
    public ApiResponse<ApiDTO.ApiDetail> getApiDetail(@PathVariable Long apiId) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                apiQueryService.getApiDetail(apiId)
        );
    }
}
