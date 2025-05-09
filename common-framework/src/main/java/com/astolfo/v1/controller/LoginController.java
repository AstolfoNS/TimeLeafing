package com.astolfo.v1.controller;

import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.dto.LoginRequest;
import com.astolfo.v1.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

}
