package com.astolfo.domain.rbac.model.valueobject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Permission {

    private String permissionName;


    public static Permission of(String permissionName) {
        return new Permission(permissionName);
    }

}
