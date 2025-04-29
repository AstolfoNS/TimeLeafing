package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN"),
    SYSTEM("SYSTEM");


    final String role;

}
