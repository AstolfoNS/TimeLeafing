package com.astolfo.domain.domain.rbac.model.valueobject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {

    private String email;


    public static Email of(String email) {
        return new Email(email);
    }

}
