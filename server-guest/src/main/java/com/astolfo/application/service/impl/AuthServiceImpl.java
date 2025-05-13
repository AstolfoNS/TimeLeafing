package com.astolfo.application.service.impl;

import com.astolfo.application.service.AuthService;
import com.astolfo.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.infrastructure.common.util.JwtUtil;
import com.astolfo.infrastructure.common.util.RedisCacheUtil;
import com.astolfo.webinterface.dto.LoginRequest;
import com.astolfo.webinterface.vo.LoginResponse;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.webinterface.vo.MenuInfo;
import com.astolfo.webinterface.vo.RoleInfo;
import com.astolfo.webinterface.vo.converter.MenuInfoConverter;
import com.astolfo.webinterface.vo.converter.RoleInfoConverter;
import com.astolfo.security.userdetails.LoginUserDetails;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PermissionRepository menuRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MenuInfoConverter menuInfoConverter;

    @Resource
    private RoleInfoConverter roleInfoConverter;

    @Resource
    private RedisCacheUtil redisCacheUtil;


    @Override
    public ResponseResult<LoginResponse> login(LoginRequest loginRequest) {
        try {
            String UsernameOrEmailAddress = loginRequest.getUsernameOrEmailAddress();

            String password = loginRequest.getPassword();

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UsernameOrEmailAddress, password));

            LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();

            redisCacheUtil.setObject(RedisCacheConstant.Login_USER_PERFIX.concat(loginUserDetails.getId().toString()), loginUserDetails);

            String token = jwtUtil.generateToken(loginUserDetails);

            String username = loginUserDetails.getUsername();

            List<RoleInfo> roleInfoList = roleInfoConverter.toVo(roleRepository.findUserRoleListById(loginUserDetails.getId()));

            List<MenuInfo> menuInfoList = menuInfoConverter.toVo(menuRepository.findUserMenuListById(loginUserDetails.getId()));

            return ResponseResult.okResult(HttpCode.LOGIN_SUCCESS, new LoginResponse(token, username, roleInfoList, menuInfoList));
        } catch (RuntimeException exception) {
            return ResponseResult.errorResult(HttpCode.LOGIN_FAILED);
        }
    }

    @Override
    public ResponseResult<LogoutResponse> logout() {
        try {
            LoginUserDetails loginUserDetails = (LoginUserDetails) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            redisCacheUtil.delete(RedisCacheConstant.Login_USER_PERFIX.concat(loginUserDetails.getId().toString()));

            return ResponseResult.okResult(HttpCode.LOGOUT_SUCCESS, new LogoutResponse(loginUserDetails.getUsername()));
        } catch (RuntimeException exception) {
            return ResponseResult.errorResult(HttpCode.LOGOUT_FAILED);
        }
    }
}
