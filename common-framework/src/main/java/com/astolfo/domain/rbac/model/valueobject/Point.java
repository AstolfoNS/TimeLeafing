package com.astolfo.domain.rbac.model.valueobject;

import com.astolfo.infrastructure.common.util.shared.EnumStringMappingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum Point {

    BUTTON("BUTTON"),
    MENU("MENU");


    private final String point;

    private static final Map<String, Point> MAP = EnumStringMappingUtil.buildMapping(Point.class, "getPoint");


    public static Point of(String point) {
        return MAP.get(point);
    }

}
