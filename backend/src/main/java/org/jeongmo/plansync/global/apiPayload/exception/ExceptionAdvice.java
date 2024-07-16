package org.jeongmo.plansync.global.apiPayload.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jeongmo.plansync.global.apiPayload.CustomResponse;
import org.jeongmo.plansync.global.apiPayload.code.BaseErrorCode;
import org.jeongmo.plansync.global.apiPayload.code.ErrorCode;
import org.jeongmo.plansync.global.apiPayload.dto.CustomErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity onMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest webRequest) {
        Map<String, String> fail = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                fieldError -> fail.put(fieldError.getField(), Optional.ofNullable(fieldError.getDefaultMessage()).orElse(""))
        );

        CustomErrorResponseDTO customDTO = ErrorCode._BAD_REQUEST.getCustomDTO();
        CustomResponse<Object> body = CustomResponse.onFailure(
                customDTO.getStatus().value(),
                customDTO.getMessage(),
                customDTO.getCode(),
                fail.toString());
        return super.handleExceptionInternal(
                e,
                body,
                HttpHeaders.EMPTY,
                customDTO.getStatus(),
                webRequest
        );
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity onGeneralException(GeneralException generalException, HttpServletRequest request) {
        CustomErrorResponseDTO errorResponseDTO = generalException.getErrorDTO();
        return customHandleExceptionInternal(generalException, errorResponseDTO, null, errorResponseDTO.getStatus(),request);
    }

    @ExceptionHandler
    public ResponseEntity onException(Exception e, WebRequest webRequest) {
        this.logger.error(e.getMessage());

        return customHandleException(e, ErrorCode._INTERNAL_SERVER_ERROR, webRequest);
    }

    private ResponseEntity customHandleExceptionInternal(Exception e, CustomErrorResponseDTO dto, HttpHeaders headers, HttpStatusCode status, HttpServletRequest request) {
        CustomResponse<Object> body = CustomResponse.onFailure(status.value(), dto.getMessage(), dto.getCode(), null);
        WebRequest webRequest = new ServletWebRequest(request);

        return super.handleExceptionInternal(e, body, headers, status, webRequest);
    }

    private ResponseEntity customHandleException(Exception e, BaseErrorCode errorCode, WebRequest request) {

        CustomErrorResponseDTO errorResponseDTO = errorCode.getCustomDTO();
        CustomResponse body = CustomResponse.onFailure(errorResponseDTO.getStatus().value(), errorResponseDTO.getMessage(), errorResponseDTO.getCode(), null);
        return super.handleExceptionInternal(
                e,
                body,
                HttpHeaders.EMPTY,
                errorResponseDTO.getStatus(),
                request
        );
    }
}
