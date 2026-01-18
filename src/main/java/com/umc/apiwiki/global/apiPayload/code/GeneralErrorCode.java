package com.umc.apiwiki.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    // 기본 응답
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "요청한 리소스를 찾을 수 없습니다. 해당 경로는 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),

    // AUTH 관련 에러
    MISSING_INPUT_VALUE(HttpStatus.BAD_REQUEST, "AUTH4000", "필수적인 입력값이 누락되었습니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "AUTH4001", "이메일의 형식이 유효하지 않습니다."),
    INVALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, "AUTH4002", "비밀번호는 영문+숫자 조합 8자 이상이어야 합니다."),

    // USER 관련 에러
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "USER4000", "이미 사용 중인 이메일입니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "USER4001", "이미 사용 중인 닉네임입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
