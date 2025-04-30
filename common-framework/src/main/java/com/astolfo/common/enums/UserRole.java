package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    USER("user"),
    ADMIN("admin"),
    SYSTEM("system");


    private final String role;

}
