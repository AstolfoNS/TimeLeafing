package com.astolfo.service;

import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.dto.UserDTO;

import java.util.Map;

public interface RegisterService {

    ResponseResult<Map<String, String>> register(UserDTO userDTO);
}
