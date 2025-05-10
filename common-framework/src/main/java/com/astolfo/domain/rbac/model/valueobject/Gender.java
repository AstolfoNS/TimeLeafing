package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");


    private final String genderName;

}
