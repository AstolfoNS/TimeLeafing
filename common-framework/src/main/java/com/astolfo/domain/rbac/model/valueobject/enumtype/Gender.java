package com.astolfo.domain.rbac.model.valueobject.enumtype;

import com.astolfo.common.util.EnumStringMappingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");


    private final String genderName;

    private static final Map<String, Gender> MAP = EnumStringMappingUtil.buildMapping(Gender.class, "getGenderName");


    public static Gender get(String genderName) {
        return MAP.get(genderName);
    }
}
