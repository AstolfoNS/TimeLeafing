package com.astolfo.infrastructure.common.util;

import com.astolfo.infrastructure.security.userdetails.LoginUserDetails;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Slf4j
@Component
public class JwtUtil {

    @Resource
    private JwtEncoder jwtEncoder;

    @Resource
    private JwtDecoder jwtDecoder;

    @Value("#{jwtProperties.expire}")
    private Long expire;

    @Value("#{jwtProperties.issuer}")
    private String issuer;

    @Value("#{jwtProperties.algorithm}")
    private String algorithm;


    public String generateToken(
            LoginUserDetails loginUserDetails,
            Instant issuedAt,
            Long expiresInMillis
    ) {
        JwtClaimsSet claim = JwtClaimsSet
                .builder()
                .subject(loginUserDetails.getStringId())
                .issuedAt(issuedAt)
                .issuer(issuer)
                .expiresAt(issuedAt.plusMillis(expiresInMillis))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.with(SignatureAlgorithm.from(algorithm)).build(), claim)).getTokenValue();
    }

    public String generateToken(LoginUserDetails loginUserDetails, Long expireInMillis) {
        return generateToken(loginUserDetails, Instant.now(), expireInMillis);
    }

    public String generateToken(LoginUserDetails loginUserDetails) {
        return generateToken(loginUserDetails, expire);
    }

    public ParseToken parseToken(String token) throws JwtException {
        try {
            return new ParseToken(jwtDecoder.decode(token));
        } catch (JwtException exception) {
            log.error("无效的JWT token");

            throw new JwtException("无效的JWT token", exception);
        }
    }

    @AllArgsConstructor
    public static class ParseToken {

        private final Jwt jwt;


        public String getStringId() {
            return jwt.getSubject();
        }

        public Boolean isValid() {
            return !(Objects.nonNull(jwt.getExpiresAt()) && jwt.getExpiresAt().isBefore(Instant.now()));
        }
    }

}
