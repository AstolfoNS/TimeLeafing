package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleName {

    private String roleName;


    public static RoleName of(String roleName) {
        if (Objects.isNull(roleName)) {
            return null;
        } else {
            return new RoleName(roleName);
        }
    }

}
