package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Avatar {

    private String avatar;


    public static Avatar of(String avatar) {
        if (Objects.isNull(avatar)) {
            return null;
        } else {
            return new Avatar(avatar);
        }
    }

}
