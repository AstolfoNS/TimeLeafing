package com.astolfo.controller;

import com.astolfo.common.results.ResponseResult;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.service.LoginService;
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
    public ResponseResult<Map<String, String>> login(@RequestBody UserDTO userDTO) {
        return loginService.login(userDTO);
    }

}
