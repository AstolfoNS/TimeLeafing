package com.astolfo.controller;

import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/user")
@RestController
public class EnterController {

    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(@RequestBody UserDTO userDTO) {
        return loginService.login(userDTO);
    }

    @PostMapping("/register")
    public ResponseResult<Map<String, String>> register(@RequestBody UserDTO userDTO) {
//        System.out.println(userDTO.getUsername());
        return null;
    }

}
