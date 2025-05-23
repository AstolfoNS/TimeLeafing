package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Avatar {

    String avatar;


    public static boolean isValid(String avatar) {
        // TODO: valid

        return true;
    }

    public static Avatar of(String avatar) {
        if (isValid(avatar)) {
            return new Avatar(avatar);
        } else {
            throw new IllegalArgumentException("Invalid avatar: " + avatar);
        }
    }

}
