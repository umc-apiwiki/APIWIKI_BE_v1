package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.ProfileReqDTO;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


public interface ProfileControllerDocs {
    @Operation(
            summary = "닉네임 중복 확인 API By 악어",
            description = """
                    닉네임 중복 여부를 확인합니다.
                    
                    - 현재 로그인한 사용자의 닉네임은 제외됩니다.
                    - 중복일 경우 에러(USER4001)를 반환합니다.
                    """
    )
    @GetMapping("/profile/nickname/check")
    ApiResponse<Void> checkNicknameDuplicate(
            @Parameter(description = "중복 확인할 닉네임", required = true)
            @RequestParam String nickname,

            @Parameter(hidden = true)
            @AuthenticationPrincipal Long userId
    );

    @Operation(
            summary = "프로필 수정 API By 악어",
            description = """
                    프로필 정보를 수정합니다.
                    
                    - 닉네임 변경
                    - 비밀번호 변경
                    - 둘 중 하나 또는 동시에 변경 가능
                    - 변경할 값이 없으면 에러(USER4004)를 반환합니다.
                    """
    )
    @PatchMapping("/profile")
    ApiResponse<Void> updateProfile(
            @Parameter(hidden = true)
            @AuthenticationPrincipal Long userId,

            @RequestBody ProfileReqDTO.Update request
    );

    @Operation(
            summary = "회원 탈퇴 API By 악어",
            description = """
                    회원 탈퇴를 진행합니다.
                    
                    - 사용자는 즉시 삭제됩니다 (하드 딜리트)
                    - 복구 불가능
                    """
    )
    @DeleteMapping("/profile")
    ApiResponse<Void> deleteUser(
            @Parameter(hidden = true)
            @AuthenticationPrincipal Long userId
    );
}
