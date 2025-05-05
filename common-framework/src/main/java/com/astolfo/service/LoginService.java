package com.astolfo.service;

import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.dto.UserDTO;

import java.util.Map;

public interface LoginService {

    ResponseResult<Map<String, String>> login(UserDTO userDTO);

}
