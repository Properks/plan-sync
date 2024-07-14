package org.jeongmo.plansync.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.jeongmo.plansync.global.apiPayload.code.BaseSuccessCode;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "status", "message", "code", "data"})
public class CustomResponse<T> {

    @JsonProperty("isSuccess")
    private boolean isSuccess;
    @JsonProperty("status")
    private int status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private String code;
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode code, T data) {
        return new CustomResponse<>(true, code.getCustomDTO().getStatus().value(), code.getCustomDTO().getMessage(), code.getCustomDTO().getCode(), data);
    }

    public static <T> CustomResponse<T> of(boolean isSuccess, int status, String message, String code, T data) {
        return new CustomResponse<>(isSuccess, status, message, code, data);
    }

    public static <T> CustomResponse<T> onFailure(int status, String message, String code, T data) {
        return new CustomResponse<>(false, status, message, code, data);
    }
}
