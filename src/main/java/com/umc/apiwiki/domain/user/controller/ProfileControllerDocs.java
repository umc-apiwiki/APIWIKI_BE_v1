package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.ProfileReqDTO;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Profile", description = "프로필 관리 API")
public interface ProfileControllerDocs {

    @Operation(
            summary = "닉네임 중복 확인 API By 악어",
            description = """
                    닉네임 중복 여부를 확인합니다.

                    - JWT 인증이 필요합니다.
                    - 현재 로그인한 사용자의 닉네임은 제외됩니다.
                    - 중복일 경우 USER4001 에러를 반환합니다.
                    """
    )
    @GetMapping("/profile/nickname/check")
    ApiResponse<Void> checkNicknameDuplicate(
            @RequestParam String nickname,

            @Parameter(hidden = true)
            @AuthenticationPrincipal String email
    );

    @Operation(
            summary = "프로필 수정 API By 악어",
            description = """
                    프로필 정보를 수정합니다.

                    - 닉네임 / 비밀번호 부분 수정 가능
                    - 변경 값이 없으면 USER4004 에러
                    - JWT 인증 필요
                    """
    )
    @PatchMapping("/profile")
    ApiResponse<Void> updateProfile(
            @Parameter(hidden = true)
            @AuthenticationPrincipal String email,

            @RequestBody ProfileReqDTO.Update request
    );

    @Operation(
            summary = "회원 탈퇴 API By 악어",
            description = """
                    회원 탈퇴를 진행합니다.

                    - 하드 딜리트
                    - 복구 불가
                    - JWT 인증 필요
                    """
    )
    @DeleteMapping("/profile")
    ApiResponse<Void> deleteUser(
            @Parameter(hidden = true)
            @AuthenticationPrincipal String email
    );
}