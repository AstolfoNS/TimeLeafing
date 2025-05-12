package com.astolfo.presentation.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LoginResponse {

    private String token;

    private List<String> authorities;

}
