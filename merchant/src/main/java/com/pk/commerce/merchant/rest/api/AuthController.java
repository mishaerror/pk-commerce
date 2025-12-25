package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.config.JwtService;
import com.pk.commerce.merchant.db.MerchantEntity;
import com.pk.commerce.merchant.domain.admin.MerchantRegistrationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final MerchantRegistrationService merchantRegistrationService;

    private final JwtService jwtService;


    public AuthController(MerchantRegistrationService merchantRegistrationService, JwtService jwtService) {
        this.merchantRegistrationService = merchantRegistrationService;
        this.jwtService = jwtService;
    }

    @GetMapping(path = "/me", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> authMeResponse(@RequestHeader(name = "X-AUTH-TOKEN", defaultValue = "") String authorizationHeader) {

        if (StringUtils.isEmpty(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Jwt token;
        try {
            token = jwtService.decode(authorizationHeader.trim());
        } catch (JwtValidationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = token.getSubject();

        Optional<MerchantEntity> merchantEntity = merchantRegistrationService.merchantEmailExists(email);
        if (merchantEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String name = token.getClaim("name").toString();

        String picture = token.getClaim("picture").toString();

        String exp = token.getExpiresAt().toString();

        String ref = merchantEntity.map(entity -> entity.getRef().toString()).orElse("");
        name = merchantEntity.map(MerchantEntity::getName).orElse(name);

        return ResponseEntity.status(HttpStatus.OK)
                .header("X-AUTH-TOKEN", authorizationHeader)
                .body(
                        """
                                 {
                                  "ref": "%s",
                                  "name": "%s",
                                  "email": "%s",
                                  "exp": "%s",
                                  "picture": "%s",
                                  "registered": "%s"
                                  }
                                """.formatted(ref, name, email, exp, picture, merchantEntity.get().isRegistered()));
    }
}
