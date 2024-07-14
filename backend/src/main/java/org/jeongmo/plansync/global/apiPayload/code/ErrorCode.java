package org.jeongmo.plansync.global.apiPayload.code;

import lombok.AllArgsConstructor;
import org.jeongmo.plansync.global.apiPayload.dto.CustomErrorResponseDTO;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {

    _BAD_REQUEST(false, HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "COMMON400"),
    _UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED, "인증되지 않았습니다.", "COMMON401"),
    _FORBIDDEN(false, HttpStatus.FORBIDDEN, "권한이 없습니다.", "COMMON403"),
    _NOT_FOUND(false, HttpStatus.NOT_FOUND, "찾지 못했습니다.", "COMMON404"),
    _INTERNAL_SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "서버 관리자에게 문의하세요", "COMMON500"),
    ;

    private final boolean isSuccess;
    private final HttpStatus status;
    private final String message;
    private final String code;

    @Override
    public CustomErrorResponseDTO getCustomDTO() {
        return CustomErrorResponseDTO.builder()
                .isSuccess(isSuccess)
                .status(status)
                .message(message)
                .code(code)
                .build();
    }
}
