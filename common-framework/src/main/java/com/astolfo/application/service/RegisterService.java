package com.astolfo.application.service;

import com.astolfo.application.dto.RegisterRequest;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.infrastructure.common.response.ResponseResult;

public interface RegisterService {

    ResponseResult<Void> register(RegisterRequest registerRequest);

    boolean checkNewUser(User.NewUser newUser);

}
