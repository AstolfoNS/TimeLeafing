package com.astolfo.domain.user.model.valueobject;

import lombok.Getter;

@Getter
public class Email {

    private final String emailAddress;


    private Email(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }

        this.emailAddress = emailAddress;
    }

    public static Email of(String emailAddress) {
        return new Email(emailAddress);
    }

}
