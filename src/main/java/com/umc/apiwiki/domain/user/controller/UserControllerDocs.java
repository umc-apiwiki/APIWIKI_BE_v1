package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.UserReqDTO;
import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerDocs {

    @Operation(
            summary = "회원가입 API By 이노",
            description = "회원가입 정보를 받아 새로운 유저를 등록합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/auth/signup")
    ApiResponse<UserResDTO.LoginRes> signUp(
            @RequestBody @Valid UserReqDTO.Signup dto
    );

    @Operation(
            summary = "로그인 API By 이노",
            description = "회원의 정보를 받아 로그인합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/auth/login")
    ApiResponse<UserResDTO.LoginRes> login(
            @RequestBody @Valid UserReqDTO.LoginReq dto
    );

    @Operation(
            summary = "로그아웃 API By 이노",
            description = """
                    로그아웃합니다.<br>
                    실제 로그아웃 로직(토큰 블랙리스트 등)은 현재 백엔드 상에서 구현되어 있지 않습니다.<br>
                    <br>
                    **[프론트엔드 동작]**<br>
                    프론트엔드 측에서 해당 응답을 받으면 로컬 스토리지/쿠키의 토큰을 삭제하면 됩니다.
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/auth/logout")
    ApiResponse<String> logout();

    @Operation(
            summary = "내 프로필 조회 API By 제인",
            description = """
                    로그인한 사용자의 프로필 정보를 조회합니다.<br>
                    JWT 인증이 필요하며, 토큰에 담긴 이메일(subject)을 기반으로 사용자를 식별합니다.<br>
                    <br>
                    **[응답 정보]**<br>
                    - nickname: 사용자 닉네임
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자 정보 없음")
    })
    @GetMapping("/users/me")
    ApiResponse<UserResDTO.MyProfileRes> getMyProfile();
}
