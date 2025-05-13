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


    private final String httpMethod;

    private static final Map<String, HttpMethod> MAP = EnumStringMappingUtil.buildMapping(HttpMethod.class, "getHttpMethod");


    public static HttpMethod of(String httpMethod) {
        return MAP.get(httpMethod);
    }

}
