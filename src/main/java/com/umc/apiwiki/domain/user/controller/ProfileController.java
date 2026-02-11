package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.ProfileReqDTO;
import com.umc.apiwiki.domain.user.service.command.ProfileService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/nickname/check")
    public ApiResponse<Void> checkNicknameDuplicate(
            @RequestParam String nickname,
            @AuthenticationPrincipal(expression = "username") String email
    ) {
        profileService.checkNicknameDuplicate(nickname, email);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @PatchMapping
    public ApiResponse<Void> updateProfile(
            @AuthenticationPrincipal(expression = "username") String email,
            @RequestBody ProfileReqDTO.Update request
    ) {
        profileService.updateProfile(email, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @DeleteMapping
    public ApiResponse<Void> deleteUser(
            @AuthenticationPrincipal(expression = "username") String email
    ) {
        profileService.deleteUser(email);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
