package com.astolfo.v1.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN"),
    SYSTEM("SYSTEM");


    private final String roleName;

}
