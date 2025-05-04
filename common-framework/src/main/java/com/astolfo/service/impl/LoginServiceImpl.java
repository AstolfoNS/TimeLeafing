package com.astolfo.service.impl;

import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseResult<Void> login(UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        if (Objects.isNull(authentication)) {
            throw new UsernameNotFoundException("Username or password is incorrect.");
        }

        return null;
    }


}
