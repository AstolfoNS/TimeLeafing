package com.astolfo.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {

    private String usernameOrEmailAddress;

    private String password;

}
