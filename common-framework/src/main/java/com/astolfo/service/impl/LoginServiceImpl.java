package com.astolfo.service.impl;

import com.astolfo.common.constants.RedisCacheConstant;
import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.common.utils.JwtUtil;
import com.astolfo.common.utils.RedisCacheUtil;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.security.entity.LoginUser;
import com.astolfo.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisCacheUtil redisCacheUtil;


    @Override
    public ResponseResult<Map<String, String>> login(UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        System.out.println(loginUser.getUsername());
        System.out.println(loginUser.getPassword());

        redisCacheUtil.setObject(RedisCacheConstant.Login_USER_PERFIX.concat(loginUser.getId().toString()), loginUser);

        return ResponseResult.okResult(HttpCode.SUCCESS, Map.of("token", jwtUtil.generateToken(loginUser)));
    }


}
