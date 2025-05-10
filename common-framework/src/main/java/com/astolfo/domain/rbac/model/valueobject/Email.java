package com.astolfo.domain.rbac.model.valueobject;

import lombok.Getter;

@Getter
public class Email {

    private final String emailAddress;


    private Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public static Email of(String emailAddress) {
        return new Email(emailAddress);
    }

}
