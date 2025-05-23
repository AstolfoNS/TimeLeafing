package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UrlPattern {

    private String urlPattern;


    public static boolean isValid(String urlPattern) {
        // TODO: valid

        return true;
    }

    public static UrlPattern of(String urlPattern) {
        if (isValid(urlPattern)) {
            return new UrlPattern(urlPattern);
        } else {
            throw new IllegalArgumentException("Invalid urlPattern: " + urlPattern);
        }
    }

}
