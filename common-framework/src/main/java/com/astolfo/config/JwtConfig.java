package com.astolfo.config;

import com.astolfo.common.constants.JwtConstant;
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


    @Bean
    public SecretKeySpec secretKeySpec() {
        return new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), JwtConstant.algorithm.getAlgorithm());
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
