package com.umc.apiwiki.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umc.apiwiki.global.apiPayload.code.BaseErrorCode;
import com.umc.apiwiki.global.apiPayload.code.BaseSuccessCode;
import com.umc.apiwiki.global.apiPayload.dto.PageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    // 성공한 경우 (페이징 포함)
    public static <T> ApiResponse<PageResponseDTO<T>> onPageSuccess(BaseSuccessCode code, Page<T> page) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), new PageResponseDTO<>(page));
    }

    // 실패한 경우
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
