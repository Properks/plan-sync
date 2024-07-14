package org.jeongmo.plansync.global.apiPayload.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class CustomErrorResponseDTO {
    private boolean isSuccess;
    private HttpStatus status;
    private String message;
    private String code;
}
