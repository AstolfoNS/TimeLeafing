package com.astolfo.service.impl;

import com.astolfo.common.results.ResponseResult;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.service.RegisterService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public ResponseResult<Map<String, String>> register(UserDTO userDTO) {
        return null;
    }
}
