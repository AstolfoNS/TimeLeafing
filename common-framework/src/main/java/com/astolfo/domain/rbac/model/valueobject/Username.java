package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Username {

    private String username;


    public static boolean isValid(String username) {
        return username.matches("^[a-zA-Z0-9_-]{3,30}$");
    }

    public static Username of(String username) {
        return new Username(username);
    }

}
