package com.astolfo.application.service;

import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.dto.LoginRequest;
import com.astolfo.webinterface.vo.LoginResponse;
import com.astolfo.webinterface.vo.LogoutResponse;

public interface AuthService {

    ResponseResult<LoginResponse> login(LoginRequest loginRequest);

    ResponseResult<LogoutResponse> logout();

}
