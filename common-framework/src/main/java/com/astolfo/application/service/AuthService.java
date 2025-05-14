package com.astolfo.application.service;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.TokenResponse;

public interface AuthService {

    ResponseResult<TokenResponse> login(LoginRequest loginRequest);

    ResponseResult<LogoutResponse> logout();

}
