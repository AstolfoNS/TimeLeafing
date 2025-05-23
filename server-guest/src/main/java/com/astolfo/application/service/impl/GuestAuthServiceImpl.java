package com.astolfo.application.service.impl;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.util.component.JwtUtil;
import com.astolfo.infrastructure.common.util.component.RedisCacheUtil;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
import com.astolfo.infrastructure.security.util.SecurityUtil;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.application.service.AuthService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.TokenResponse;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("GuestAuthService")
public class GuestAuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Resource
    private JwtUtil jwtUtil;


    private Authentication LoginRequestAuthenticationToken(LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
    }

    private Authentication getAuthenticationByLoginRequest(LoginRequest loginRequest) {
        return authenticationManager.authenticate(LoginRequestAuthenticationToken(loginRequest));
    }

    @Override
    public ResponseResult<TokenResponse> login(LoginRequest loginRequest) {
        try {
            LoginUser loginUser = (LoginUser) getAuthenticationByLoginRequest(loginRequest).getPrincipal();

            User user = userRepository.findUserById(UserId.of(loginUser.getUserId())).orElseThrow(() -> new RuntimeException("User Not Found"));

            user.recordLogin();

            userRepository.save(user);

            redisCacheUtil.set(RedisCacheConstant.Login_USER_PERFIX.concat(loginUser.getStringId()), loginUser);

            return ResponseResult.okResult(new TokenResponse(jwtUtil.generateToken(loginUser)));
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
