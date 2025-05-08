package com.astolfo.service;

import com.astolfo.common.results.ResponseResult;
import com.astolfo.model.dto.UserDTO;

import java.util.Map;

public interface LoginService {

    ResponseResult<Map<String, String>> login(UserDTO userDTO);

}
