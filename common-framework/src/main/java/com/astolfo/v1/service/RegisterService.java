package com.astolfo.v1.service;

import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.dto.LoginRequest;

import java.util.Map;

public interface RegisterService {

    ResponseResult<Map<String, String>> register(LoginRequest userDTO);
}
