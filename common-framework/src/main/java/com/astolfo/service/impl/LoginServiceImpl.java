package com.astolfo.service.impl;

import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.result.ResponseResult;
import com.astolfo.common.utils.JwtUtil;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.security.entity.LoginUser;
import com.astolfo.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;


    @Override
    public ResponseResult<Map<String, String>> login(UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        if (Objects.isNull(authentication)) {
            throw new UsernameNotFoundException("Username or password is incorrect.");
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String token = jwtUtil.generateToken(loginUser);

        String userId = loginUser.getUser().getId().toString();

        return ResponseResult.okResult(HttpCode.SUCCESS, Map.of("token", token));
    }


}
