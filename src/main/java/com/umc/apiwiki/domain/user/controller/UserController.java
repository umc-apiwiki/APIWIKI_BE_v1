package com.umc.apiwiki.domain.user.controller;

import com.umc.apiwiki.domain.user.dto.UserReqDTO;
import com.umc.apiwiki.domain.user.dto.UserResDTO;
import com.umc.apiwiki.domain.user.service.command.UserCommandService;
import com.umc.apiwiki.global.apiPayload.ApiResponse;
import com.umc.apiwiki.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController implements UserControllerDocs {

    private final UserCommandService  userCommandService;

    @PostMapping("/auth/signup")
    @Override
    public ApiResponse<UserResDTO.Login> signUp(
            @RequestBody @Valid UserReqDTO.Signup dto
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, userCommandService.signup(dto));
    }

    @PostMapping("/auth/login")
    @Override
    public ApiResponse<UserResDTO.Login> login(
            @RequestBody @Valid UserReqDTO.Login dto
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userCommandService.Login(dto));
    }

}
