package org.jeongmo.plansync.global.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jeongmo.plansync.global.apiPayload.code.BaseErrorCode;
import org.jeongmo.plansync.global.apiPayload.dto.CustomErrorResponseDTO;

@AllArgsConstructor
@Getter
public class GeneralException extends RuntimeException{
    private BaseErrorCode code;

    CustomErrorResponseDTO getErrorDTO() {
        return this.code.getCustomDTO();
    }
}
