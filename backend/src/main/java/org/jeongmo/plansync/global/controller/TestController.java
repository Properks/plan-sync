package org.jeongmo.plansync.global.controller;

import lombok.Getter;
import org.jeongmo.plansync.global.apiPayload.CustomResponse;
import org.jeongmo.plansync.global.apiPayload.code.SuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public CustomResponse<String> helloWorld() {
        return CustomResponse.onSuccess(SuccessCode._OK, "hello world");
    }
}
