package com.astolfo.application.service;

import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.presentation.dto.LoginRequest;
import com.astolfo.presentation.vo.LoginResponse;
import com.astolfo.presentation.vo.LogoutResponse;

public interface AuthService {

    ResponseResult<LoginResponse> login(LoginRequest loginRequest);

    ResponseResult<LogoutResponse> logout();

}
