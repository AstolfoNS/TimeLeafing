package com.astolfo.application.service.impl;

import com.astolfo.application.service.AuthService;
import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.model.Role;
import com.astolfo.domain.rbac.repository.MenuRepository;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.infrastructure.common.util.JwtUtil;
import com.astolfo.infrastructure.common.util.RedisCacheUtil;
import com.astolfo.presentation.dto.LoginRequest;
import com.astolfo.presentation.vo.LoginResponse;
import com.astolfo.presentation.vo.MenuInfo;
import com.astolfo.presentation.vo.RoleInfo;
import com.astolfo.presentation.vo.common.converter.MenuInfoConverter;
import com.astolfo.presentation.vo.common.converter.RoleInfoConverter;
import com.astolfo.security.userdetails.LoginUserDetails;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private MenuInfoConverter menuInfoConverter;

    @Resource
    private RoleInfoConverter roleInfoConverter;


    @Override
    public ResponseResult<LoginResponse> login(LoginRequest loginRequest) {
        try {
            String UsernameOrEmailAddress = loginRequest.getUsernameOrEmailAddress();

            String password = loginRequest.getPassword();

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UsernameOrEmailAddress, password));

            LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();

            Long userId = loginUserDetails.getId();

            redisCacheUtil.setObject(RedisCacheConstant.Login_USER_PERFIX.concat(userId.toString()), loginUserDetails);

            String token = jwtUtil.generateToken(loginUserDetails);

            String username = loginUserDetails.getUsername();

            List<Menu> menuList = menuRepository.findUserMenuListById(userId);

            List<Role> roleList = roleRepository.findUserRoleListById(userId);

            List<MenuInfo> menuInfoList = menuInfoConverter.toVo(menuList);

            List<RoleInfo> roleInfoList = roleInfoConverter.toVo(roleList);

            LoginResponse loginResponse = new LoginResponse(token, username, roleInfoList, menuInfoList);

            return ResponseResult.okResult(HttpCode.LOGIN_SUCCESS, loginResponse);
        } catch (RuntimeException exception) {
            return ResponseResult.errorResult(HttpCode.LOGIN_FAILED);
        }
    }
}
