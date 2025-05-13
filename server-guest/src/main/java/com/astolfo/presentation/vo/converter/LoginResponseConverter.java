package com.astolfo.presentation.vo.converter;

import com.astolfo.domain.rbac.repository.MenuRepository;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.infrastructure.common.util.JwtUtil;
import com.astolfo.presentation.vo.LoginResponse;
import com.astolfo.presentation.vo.MenuInfo;
import com.astolfo.presentation.vo.RoleInfo;
import com.astolfo.security.userdetails.LoginUserDetails;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginResponseConverter {

    @Resource
    MenuRepository menuRepository;

    @Resource
    RoleRepository roleRepository;

    @Resource
    JwtUtil jwtUtil;

    @Resource
    MenuInfoConverter menuInfoConverter;

    @Resource
    RoleInfoConverter roleInfoConverter;


    public LoginResponse toVo(LoginUserDetails loginUserDetails) {
        if (loginUserDetails == null) {
            return null;
        } else {
            String token = jwtUtil.generateToken(loginUserDetails);

            String username = loginUserDetails.getUsername();

            List<MenuInfo> menuInfoList = menuInfoConverter.toVo(menuRepository.findUserMenuListById(loginUserDetails.getId()));

            List<RoleInfo> roleInfoList = roleInfoConverter.toVo(roleRepository.findUserRoleListById(loginUserDetails.getId()));

            return new LoginResponse(token, username, roleInfoList, menuInfoList);
        }
    }

}
