package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.UserReqDTO;
import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.domain.user.service.command.UserCommandService;
import com.umc.apiwiki.domain.wiki.service.query.WikiQueryService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController implements UserControllerDocs {

    private final UserCommandService userCommandService;
    private final WikiQueryService wikiQueryService;

    @PostMapping("/auth/signup")
    @Override
    public ApiResponse<UserResDTO.LoginRes> signUp(
            @RequestBody @Valid UserReqDTO.Signup dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, userCommandService.signup(dto));
    }

    @PostMapping("/auth/login")
    @Override
    public ApiResponse<UserResDTO.LoginRes> login(
            @RequestBody @Valid UserReqDTO.LoginReq dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userCommandService.Login(dto));
    }

    @PatchMapping("/auth/logout")
    @Override
    public ApiResponse<String> logout() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "로그아웃 성공");
    }

    @PostMapping("/users/me/wikis")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ApiResponse<PageResponseDTO<UserResDTO.MyWikiHistory>> viewMyWikiHistory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(required = false, defaultValue = "16") @Positive Integer size) {
        Long userId = userDetails.getUser().getId();

        return  ApiResponse.onPageSuccess(GeneralSuccessCode.OK, wikiQueryService.returnMyWikiHistory(page, size, userId));
    }
}
