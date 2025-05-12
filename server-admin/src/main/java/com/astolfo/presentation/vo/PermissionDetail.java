package com.astolfo.presentation.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PermissionDetail {

    private String permission;

    private String description;

    private String url;

    private String httpMethod;

    private String authorityType;

    private Integer orderNum;

}
