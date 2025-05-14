package com.astolfo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionDto {

    private Long id;

    private String symbol;

    private String description;

    private String url;

    private String httpMethod;

    private String point;
}
