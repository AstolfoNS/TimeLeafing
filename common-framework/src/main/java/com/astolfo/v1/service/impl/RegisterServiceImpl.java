package com.astolfo.v1.service.impl;

import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.dto.LoginRequest;
import com.astolfo.v1.service.RegisterService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public ResponseResult<Map<String, String>> register(LoginRequest userDTO) {
        return null;
    }
}
