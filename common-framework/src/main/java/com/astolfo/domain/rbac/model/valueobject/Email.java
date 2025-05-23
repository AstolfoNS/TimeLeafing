package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {

    private String email;


    public static boolean isValid(String email) {
        // TODO: valid

        return true;
    }

    public static Email of(String email) {
        if (isValid(email)) {
            return new Email(email);
        } else {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }


}
