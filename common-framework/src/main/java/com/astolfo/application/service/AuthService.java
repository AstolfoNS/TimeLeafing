package com.astolfo.application.service;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.application.dto.LogoutResponse;
import com.astolfo.application.dto.TokenResponse;
import com.astolfo.infrastructure.common.response.ResponseResult;

public interface AuthService {

    ResponseResult<TokenResponse> login(LoginRequest loginRequest);

    ResponseResult<LogoutResponse> logout();

}
