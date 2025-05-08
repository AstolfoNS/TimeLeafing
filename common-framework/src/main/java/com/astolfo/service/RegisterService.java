package com.astolfo.service;

import com.astolfo.common.results.ResponseResult;
import com.astolfo.model.dto.UserDTO;

import java.util.Map;

public interface RegisterService {

    ResponseResult<Map<String, String>> register(UserDTO userDTO);
}
