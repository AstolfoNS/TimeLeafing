package com.astolfo.common.utils;

import com.astolfo.common.constants.JwtConstant;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    @Resource
    private JwtEncoder jwtEncoder;

    @Resource
    private JwtDecoder jwtDecoder;


    @Value("${spring.security.jwt.expire}")
    private Long expire;

    @Value("${spring.security.jwt.issuer}")
    private String issuer;


    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static List<String> getAuthoritiesFromUserDetails(Collection<? extends GrantedAuthority> authorities) {
        return authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public String generateToken(
            UserDetails userDetails,
            Instant issuedAt,
            Long expiresInMillis
    ) {
        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .subject(userDetails.getUsername())
                .claim("authorities", getAuthoritiesFromUserDetails(userDetails.getAuthorities()))
                .issuedAt(issuedAt)
                .issuer(issuer)
                .expiresAt(issuedAt.plusMillis(expiresInMillis))
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JwtConstant.algorithm.getMacAlgorithm()).build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(jwsHeader, claims);

        return jwtEncoder.encode(parameters).getTokenValue();
    }

    public String generateToken(UserDetails userDetails, Long expireInMillis) {
        return generateToken(userDetails, Instant.now(), expireInMillis);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, Instant.now(), expire);
    }

    public ParseToken parseToken(String token) throws JwtException {
        try {
            return new ParseToken(jwtDecoder.decode(token));
        } catch (JwtException e) {
            throw new JwtException("Invalid Jwt Token：" + e.getMessage(), e);
        }
    }

    @AllArgsConstructor
    public static class ParseToken {

        private final Jwt jwt;


        public String getUsername() {
            return jwt.getSubject();
        }

        public List<String> getAuthorities() {
            return jwt.getClaimAsStringList("authorities");
        }

        public Boolean isValid() {
            return !(Objects.nonNull(jwt.getExpiresAt()) && jwt.getExpiresAt().isBefore(Instant.now()));
        }
    }

}