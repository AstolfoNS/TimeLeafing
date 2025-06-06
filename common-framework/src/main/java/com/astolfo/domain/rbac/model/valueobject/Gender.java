package com.astolfo.domain.rbac.model.valueobject;

import com.astolfo.infrastructure.common.util.shared.EnumStringMappingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");


    private final String gender;

    private static final Map<String, Gender> MAP = EnumStringMappingUtil.buildMapping(Gender.class, "getGender");


    public static Gender of(String gender) {
        return MAP.getOrDefault(gender, UNKNOWN);
    }
}
