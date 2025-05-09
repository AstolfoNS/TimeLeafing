package com.astolfo.v1.service.impl;

import com.astolfo.v1.common.constants.RedisCacheConstant;
import com.astolfo.v1.common.enums.HttpCode;
import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.common.utils.components.JwtUtil;
import com.astolfo.v1.common.utils.components.RedisCacheUtil;
import com.astolfo.v1.model.dto.LoginRequest;
import com.astolfo.v1.security.entity.LoginUser;
import com.astolfo.v1.service.LoginService;
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
    public ResponseResult<Map<String, String>> login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            LoginUser loginUser = (LoginUser) authentication.getPrincipal();

            redisCacheUtil.setObject(RedisCacheConstant.Login_USER_PERFIX.concat(loginUser.getStringId()), loginUser);

            return ResponseResult.okResult(HttpCode.LOGIN_SUCCESS, Map.of("token", jwtUtil.generateToken(loginUser)));
        } catch (RuntimeException exception) {
            return ResponseResult.errorResult(HttpCode.LOGIN_FAILED);
        }
    }

}
