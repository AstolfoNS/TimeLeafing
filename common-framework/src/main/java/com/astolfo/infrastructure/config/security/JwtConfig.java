package com.astolfo.infrastructure.config.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.annotation.Resource;
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

    @Value("#{jwtProperties.jwtSecret}")
    private String jwtSecret;

    @Value("#{jwtProperties.jcaAlgorithm}")
    private String jcaAlgorithm;


    @Bean
    public SecretKeySpec secretKeySpec() {
        return new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), jcaAlgorithm);
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKeySpec secretKeySpec) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKeySpec));
    }

    @Bean
    public JwtDecoder jwtDecoder(SecretKeySpec secretKeySpec) {
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }

}
