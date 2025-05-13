package com.astolfo.domain.rbac.model.valueobject.enumtype;

import com.astolfo.common.util.EnumStringMappingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum PermissionPoint {

    BUTTON("BUTTON"),
    MENU("MENU");


    private final String authorityTypeName;

    private static final Map<String, PermissionPoint> MAP = EnumStringMappingUtil.buildMapping(PermissionPoint.class, "getAuthorityTypeName");


    public static PermissionPoint get(String authorityTypeName) {
        return MAP.get(authorityTypeName);
    }

}
