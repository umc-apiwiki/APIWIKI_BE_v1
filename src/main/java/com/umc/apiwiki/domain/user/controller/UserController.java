package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.UserReqDTO;
import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.domain.user.service.command.UserCommandService;
import com.umc.apiwiki.domain.user.service.query.UserQueryService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController implements UserControllerDocs {

    private final UserCommandService  userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/auth/signup")
    @Override
    public ApiResponse<UserResDTO.LoginRes> signUp(
            @RequestBody @Valid UserReqDTO.Signup dto
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, userCommandService.signup(dto));
    }

    @PostMapping("/auth/login")
    @Override
    public ApiResponse<UserResDTO.LoginRes> login(
            @RequestBody @Valid UserReqDTO.LoginReq dto
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userCommandService.Login(dto));
    }

    @PostMapping("/auth/logout")
    @Override
    public ApiResponse<String> logout() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,"로그아웃 성공");
    }

    @GetMapping("/users/me")
    @Override
    public ApiResponse<UserResDTO.MyProfileRes> getMyProfile() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userQueryService.getMyProfile());
    }
}
