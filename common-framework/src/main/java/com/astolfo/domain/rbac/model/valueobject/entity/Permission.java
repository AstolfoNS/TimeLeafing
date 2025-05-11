package com.astolfo.domain.rbac.model.valueobject.entity;

import lombok.Getter;

@Getter
public class Permission {

    private final String permissionName;


    private Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    public static Permission of(String permissionName) {
        return new Permission(permissionName);
    }

}
