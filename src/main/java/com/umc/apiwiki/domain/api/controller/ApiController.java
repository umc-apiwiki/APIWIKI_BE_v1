package com.umc.apiwiki.domain.api.controller;

import com.umc.apiwiki.domain.api.dto.ApiDTO;
import com.umc.apiwiki.domain.api.enums.AuthType;
import com.umc.apiwiki.domain.api.enums.PricingType;
import com.umc.apiwiki.domain.api.enums.ProviderCompany;
import com.umc.apiwiki.domain.api.service.ApiQueryService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apis")
public class ApiController {

    private final ApiQueryService apiQueryService;

    @GetMapping
    public ApiResponse<PageResponseDTO<ApiDTO.ApiPreview>> searchApis(
            @RequestParam int page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Long categoryId,
//            @RequestParam(required = false) String q, // 검색어
//            @RequestParam(required = false, defaultValue = "latest") String sort, // 정렬 옵션
            @RequestParam(required = false, name = "providers") ProviderCompany providerCompany,
            @RequestParam(required = false, name = "authTypes") AuthType authType,
            @RequestParam(required = false, name = "pricingTypes") PricingType pricingType,
            @RequestParam(required = false) BigDecimal minRating
    ) {

        Page<ApiDTO.ApiPreview> resultPage = apiQueryService.searchApis(
                page,
                size,
                categoryId,
//                q,
//                sort,
                providerCompany,
                authType,
                pricingType,
                minRating
        );

        return ApiResponse.onPageSuccess(GeneralSuccessCode.OK, resultPage);
    }
}