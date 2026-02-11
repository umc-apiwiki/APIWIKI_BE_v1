package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.ProfileReqDTO;
import com.umc.apiwiki.domain.user.service.command.ProfileCommandService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import com.umc.apiwiki.global.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController implements ProfileControllerDocs {

    private final ProfileCommandService profileCommandService;

    @Override
    @GetMapping("/nickname/check")
    public ApiResponse<Void> checkNicknameDuplicate(@RequestParam String nickname, @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        profileCommandService.checkNicknameDuplicate(nickname, userDetails.getUsername());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @Override
    @PatchMapping
    public ApiResponse<Void> updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ProfileReqDTO.Update request) {
        profileCommandService.updateProfile(userDetails.getUsername(), request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @Override
    @DeleteMapping
    public ApiResponse<Void> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        profileCommandService.deleteUser(userDetails.getUsername());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
