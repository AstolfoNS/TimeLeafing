package com.astolfo.controller;

import com.astolfo.common.results.ResponseResult;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.service.RegisterService;
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
    public ResponseResult<Map<String, String>> register(@RequestBody UserDTO userDTO) {
        return registerService.register(userDTO);
    }
}
