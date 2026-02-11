package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.ProfileReqDTO;
import com.umc.apiwiki.domain.user.service.command.ProfileCommandService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController implements ProfileControllerDocs {

    private final ProfileCommandService profileCommandService;

    @Override
    @GetMapping("/nickname/check")
    public ApiResponse<Void> checkNicknameDuplicate(@RequestParam String nickname, @AuthenticationPrincipal String email) {
        profileCommandService.checkNicknameDuplicate(nickname, email);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @Override
    @PatchMapping
    public ApiResponse<Void> updateProfile(@AuthenticationPrincipal String email, @RequestBody ProfileReqDTO.Update request) {
        profileCommandService.updateProfile(email, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @Override
    @DeleteMapping
    public ApiResponse<Void> deleteUser(@AuthenticationPrincipal String email) {
        profileCommandService.deleteUser(email);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}

