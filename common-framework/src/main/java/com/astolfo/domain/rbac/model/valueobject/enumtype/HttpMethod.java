package com.astolfo.domain.rbac.model.valueobject.enumtype;

import com.astolfo.common.util.EnumStringMappingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum HttpMethod {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");


    private final String httpMethodName;

    private static final Map<String, HttpMethod> MAP = EnumStringMappingUtil.buildMapping(HttpMethod.class, "getHttpMethodName");


    public static HttpMethod get(String httpMethodName) {
        return MAP.get(httpMethodName);
    }

}
