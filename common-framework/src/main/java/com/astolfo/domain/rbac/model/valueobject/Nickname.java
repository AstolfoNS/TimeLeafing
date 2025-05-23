package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Nickname {

    private String nickname;


    public static boolean isValid(String nickname) {
        // TODO: valid

        return true;
    }

    public static Nickname of(String nickname) {
        if (isValid(nickname)) {
            return new Nickname(nickname);
        } else {
            throw new IllegalArgumentException("Invalid nickname: " + nickname);
        }
    }

}
