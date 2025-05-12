package com.astolfo.domain.rbac.model.valueobject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {

    private String emailAddress;


    public static Email of(String emailAddress) {
        return new Email(emailAddress);
    }

}
