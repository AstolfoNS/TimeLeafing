package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityType {
    BUTTON("BUTTON"),
    MENU("MENU");


    private final String authorityTypeName;

}
