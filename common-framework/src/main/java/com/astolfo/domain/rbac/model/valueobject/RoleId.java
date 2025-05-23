package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleId {

    private Long roleId;


    public static boolean isValid(Long roleId) {
        // TODO: valid

        return true;
    }

    public static RoleId of(Long roleId) {
        if (isValid(roleId)) {
            return new RoleId(roleId);
        } else {
            throw new IllegalArgumentException("Invalid roleId: " + roleId);
        }
    }

}
