package com.astolfo.application.service.impl;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.util.JwtUtil;
import com.astolfo.infrastructure.common.util.RedisCacheUtil;
import com.astolfo.infrastructure.security.userdetails.LoginUserDetails;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.application.service.AuthService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.TokenResponse;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GuestAuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Resource
    private JwtUtil jwtUtil;


    @Override
    public ResponseResult<TokenResponse> login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();

        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();

            redisCacheUtil.setObject(RedisCacheConstant.Login_USER_PERFIX.concat(loginUserDetails.getStringId()), loginUserDetails);

            return ResponseResult.okResult(new TokenResponse(loginUserDetails.getUsername(), jwtUtil.generateToken(loginUserDetails)));
        } catch (RuntimeException exception) {
            return ResponseResult.errorResult(HttpCode.LOGIN_FAILED);
        }
    }

    @Override
    public ResponseResult<LogoutResponse> logout() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();

            redisCacheUtil.delete(RedisCacheConstant.Login_USER_PERFIX.concat(loginUserDetails.getStringId()));

            return ResponseResult.okResult(new LogoutResponse(loginUserDetails.getUsername()));
        } catch (RuntimeException exception) {
            return ResponseResult.errorResult(HttpCode.LOGOUT_FAILED);
        }
    }
}
