package com.astolfo.presentation.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginRequest {

    private String usernameOrEmail;

    private String password;

}
