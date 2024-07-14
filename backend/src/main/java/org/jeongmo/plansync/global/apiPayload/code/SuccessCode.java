package org.jeongmo.plansync.global.apiPayload.code;


import lombok.AllArgsConstructor;
import org.jeongmo.plansync.global.apiPayload.dto.CustomSuccessResponseDTO;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum SuccessCode implements BaseSuccessCode {

    _OK(true, HttpStatus.OK, "요청이 성공했습니다.", "COMMON200"),
    _CREATED(true, HttpStatus.CREATED, "성공적으로 생성하였습니다.", "COMMON 201"),
    ;

    private final boolean isSuccess;
    private final HttpStatus status;
    private final String message;
    private final String code;

    @Override
    public CustomSuccessResponseDTO getCustomDTO() {
        return CustomSuccessResponseDTO.builder()
                .isSuccess(isSuccess)
                .status(status)
                .message(message)
                .code(code)
                .build();
    }
}
