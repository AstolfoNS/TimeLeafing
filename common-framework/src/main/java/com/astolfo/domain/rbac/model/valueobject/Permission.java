package com.astolfo.domain.rbac.model.valueobject;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Permission {

    private final String permissionName;


    private Permission(String permissionName) {
        if (StringUtils.hasText(permissionName)) {
            throw new IllegalArgumentException("Permission is illegal");
        }

        this.permissionName = permissionName;
    }

    public static Permission of(String permissionName) {
        return new Permission(permissionName);
    }

}
