package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionId {

    private Long permissionId;

    public static boolean isValid(Long permissionId) {
        return true;
    }


    public static PermissionId of(Long permissionId) {
        if (isValid(permissionId)) {
            return new PermissionId(permissionId);
        } else {
            throw new IllegalArgumentException("Invalid permissionId: " + permissionId);
        }
    }

}
