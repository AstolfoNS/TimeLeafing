package com.astolfo.service;

import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.dto.UserDTO;

public interface LoginService {

    ResponseResult<Void> login(UserDTO userDTO);

}
