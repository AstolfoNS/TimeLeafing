package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordHash {

    private String passwordHash;


    public static boolean isValid(String passwordHash) {
        // TODO: valid

        return true;
    }

    public static PasswordHash of(String passwordHash) {
        if (isValid(passwordHash)) {
            return new PasswordHash(passwordHash);
        } else {
            throw new IllegalArgumentException("Invalid passwordHash: " + passwordHash);
        }
    }

}
