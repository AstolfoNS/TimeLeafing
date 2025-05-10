package com.astolfo.infrastructure.common.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

@AllArgsConstructor
@Getter
public enum JwtAlgorithm {

    HmacSHA256(MacAlgorithm.HS256, "HmacSHA256"),
    HmacSHA512(MacAlgorithm.HS512, "HmacSHA512");


    private final MacAlgorithm macAlgorithm;

    private final String algorithm;

}
