package com.pk.commerce.merchant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtService {

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    public JwtService(@Value("${custom.security.oauth2.jwt-secret}") String jwtSecret) {
        SecretKeySpec secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HS256");
        jwtEncoder = NimbusJwtEncoder
                .withSecretKey(secretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
        jwtDecoder = NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    public Jwt decode(String input) {
        return jwtDecoder.decode(input);
    }

    public Jwt generateToken(String email, String name, String picture, String scope) {
        return jwtEncoder.encode(JwtEncoderParameters.from(JwtClaimsSet.builder()
                .subject(email)
                .expiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
                .issuedAt(Instant.now())
                .claim("name", name)
                .claim("picture", picture)
                .claim("scope", scope)
                .build()));
    }
}
