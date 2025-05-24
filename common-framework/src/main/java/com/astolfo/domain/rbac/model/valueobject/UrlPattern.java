package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UrlPattern {

    private String urlPattern;


    public static UrlPattern of(String urlPattern) {
        if (Objects.isNull(urlPattern)) {
            return null;
        } else {
            return new UrlPattern(urlPattern);
        }
    }

}
