package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleName {

    private String roleName;

    public static boolean isValid(String roleName) {
        // TODO: valid

        return true;
    }

    public static RoleName of(String roleName) {
        if (isValid(roleName)) {
            return new RoleName(roleName);
        } else {
            throw new IllegalArgumentException("Invalid roleName: " + roleName);
        }
    }

}
