package com.astolfo.domain.user.model.valueobject;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Email {

    private final String emailAddress;


    private Email(String emailAddress) {
        if (StringUtils.hasText(emailAddress)) {
            throw new IllegalArgumentException("Email address is illegal");
        }

        this.emailAddress = emailAddress;
    }

    public static Email of(String emailAddress) {
        return new Email(emailAddress);
    }

}
