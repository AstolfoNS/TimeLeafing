package com.astolfo.infrastructure.config.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtProperties {

    @Value("${spring.security.jwt.key}")
    private String jwtSecret;

    @Value("${spring.security.jwt.expire}")
    private Long expire;

    @Value("${spring.security.jwt.issuer}")
    private String issuer;

    @Value("${spring.security.jwt.algorithm}")
    private String algorithm;

}
