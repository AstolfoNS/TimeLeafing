package com.astolfo.domain.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    BUTTON("BUTTON"),
    MENU("MENU");


    private final String typeName;

}
