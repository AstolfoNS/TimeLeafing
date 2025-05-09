package com.astolfo.v1.controller;

import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.dto.LoginRequest;
import com.astolfo.v1.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {

    @Resource
    private RegisterService registerService;


    @PostMapping("/register")
    public ResponseResult<Map<String, String>> register(@RequestBody LoginRequest userDTO) {
        return registerService.register(userDTO);
    }
}
