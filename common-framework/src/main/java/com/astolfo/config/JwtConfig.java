package com.astolfo.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {

    @Value("${spring.security.jwt.key}")
    private String jwtSecret;

    @Value("HmacSHA256")
    private String algorithm;


    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(
                new ImmutableSecret<>(new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), algorithm))
        );
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
                new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), algorithm)
        ).build();
    }
}
