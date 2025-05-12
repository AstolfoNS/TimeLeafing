package com.astolfo.presentation.vo;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LoginResponse {

    private String token;

    private String username;

    private List<RoleInfo> roles;

    private List<MenuInfo> permissions;

}
