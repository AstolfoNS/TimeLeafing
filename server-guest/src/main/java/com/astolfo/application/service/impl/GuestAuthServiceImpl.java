package com.astolfo.application.service.impl;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.util.component.JwtUtil;
import com.astolfo.infrastructure.common.util.component.RedisCacheUtil;
import com.astolfo.infrastructure.security.userdetails.LoginUserDetails;
import com.astolfo.infrastructure.security.util.SecurityUtil;
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

@Service("GuestAuthService")
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

            redisCacheUtil.set(RedisCacheConstant.Login_USER_PERFIX.concat(loginUserDetails.getStringId()), loginUserDetails);

            return ResponseResult.okResult(new TokenResponse(loginUserDetails.getUsername(), jwtUtil.generateToken(loginUserDetails)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.LOGIN_FAILED);
        }
    }

    @Override
    public ResponseResult<LogoutResponse> logout() {
        try {
            redisCacheUtil.delete(RedisCacheConstant.Login_USER_PERFIX.concat(SecurityUtil.getRequiredCurrentUserId().toString()));

            return ResponseResult.okResult(new LogoutResponse(SecurityUtil.getRequiredCurrentUserName()));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.LOGOUT_FAILED);
        }
    }
}
