package com.astolfo.domain.rbac.model.valueobject.enumtype;

import com.astolfo.common.util.EnumStringMappingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum AuthorityType {

    BUTTON("BUTTON"),
    MENU("MENU");


    private final String authorityTypeName;

    private static final Map<String, AuthorityType> MAP = EnumStringMappingUtil.buildMapping(AuthorityType.class, "getAuthorityTypeName");


    public AuthorityType get(String authorityTypeName) {
        return MAP.get(authorityTypeName);
    }

}
