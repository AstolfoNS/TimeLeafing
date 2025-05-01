package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    MALE("male"),
    FEMALE("female"),
    UNKNOWN("unknown");


    private final String genderName;

}
