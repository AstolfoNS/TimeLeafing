package com.astolfo.webinterface.controller;

import com.astolfo.application.dto.RegisterRequest;
import com.astolfo.application.service.RegisterService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Resource
    RegisterService registerService;


    @PostMapping("/register")
    public ResponseResult<Void> register(@RequestBody RegisterRequest registerRequest) {
        return registerService.register(registerRequest);
    }
}
