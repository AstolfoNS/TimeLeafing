package com.astolfo.application.service.impl;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.application.service.AuthenticationService;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.service.UserService;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
import com.astolfo.infrastructure.security.util.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserService userService;


    @Override
    public Authentication LoginRequestAuthenticationToken(LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @Override
    public Authentication getAuthenticationByLoginRequest(LoginRequest loginRequest) {
        return authenticationManager.authenticate(LoginRequestAuthenticationToken(loginRequest));
    }

    @Override
    public LoginUser authenticateByLoginRequest(LoginRequest loginRequest) {
        return (LoginUser) getAuthenticationByLoginRequest(loginRequest).getPrincipal();
    }

    @Override
    public User getCurrentUser() throws RuntimeException {
        return userService.findUserById(UserId.of(SecurityUtil.getRequiredCurrentUserId()));
    }

    @Override
    public User getCurrentUserWithoutRoleIdList() throws RuntimeException {
        return userService.findUserWithoutRoleIdListById(UserId.of(SecurityUtil.getRequiredCurrentUserId()));
    }

}
