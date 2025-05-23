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
        // TODO: valid

        return true;
    }

    public static Username of(String username) {
        if (isValid(username)) {
            return new Username(username);
        } else {
            throw new IllegalArgumentException("Invalid username: " + username);
        }
    }

}
