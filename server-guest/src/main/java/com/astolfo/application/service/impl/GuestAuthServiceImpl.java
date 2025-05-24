package com.astolfo.application.service.impl;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.application.service.AuthenticationService;
import com.astolfo.application.service.UserSessionService;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserService;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.util.component.JwtUtil;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
import com.astolfo.infrastructure.security.util.SecurityUtil;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.application.service.AuthService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.TokenResponse;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("GuestAuthService")
public class GuestAuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationService authenticationService;

    @Resource
    private UserSessionService userSessionService;

    @Resource
    private UserService userService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private JwtUtil jwtUtil;


    @Override
    public ResponseResult<TokenResponse> login(LoginRequest loginRequest) {
        try {
            LoginUser loginUser = authenticationService.authenticateByLoginRequest(loginRequest);

            User user = userService.findUserWithoutRoleIdListById(UserId.of(loginUser.getUserId()));

            user.recordLogin();

            userRepository.save(user);

            userSessionService.cacheLoginUser(loginUser);

            return ResponseResult.okResult(new TokenResponse(jwtUtil.generateToken(loginUser)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.LOGIN_FAILED.getCode(), exception.getMessage());
        }
    }

    @Override
    public ResponseResult<LogoutResponse> logout() {
        try {
            LoginUser loginUser = SecurityUtil.getRequiredLoginUser();

            userSessionService.cacheDeleteLoginUser(loginUser.getUserId());

            return ResponseResult.okResult(new LogoutResponse(loginUser.getUsername()));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.LOGOUT_FAILED);
        }
    }
}
