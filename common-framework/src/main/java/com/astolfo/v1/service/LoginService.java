package com.astolfo.v1.service;

import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.model.dto.LoginRequest;

import java.util.Map;

public interface LoginService {

    ResponseResult<Map<String, String>> login(LoginRequest loginRequest);

}
