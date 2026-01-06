package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.config.JwtService;
import com.pk.commerce.merchant.db.MerchantEntity;
import com.pk.commerce.merchant.domain.admin.MerchantRegistrationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final MerchantRegistrationService merchantRegistrationService;

    private final JwtService jwtService;

    public AuthController(MerchantRegistrationService merchantRegistrationService, JwtService jwtService) {
        this.merchantRegistrationService = merchantRegistrationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) throws IOException {
        Cookie authCookie = new Cookie("Auth", null);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setAttribute("SameSite", "None");
        authCookie.setMaxAge(0);
        response.addCookie(authCookie);
        //response.sendRedirect("/");
        return "";
    }

    @GetMapping(path = "/me", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> authMeResponse(@CookieValue(value = "Auth") String authCookie) {
        String email = SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null ?
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
                : null;

        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<MerchantEntity> merchantEntity = merchantRegistrationService.merchantEmailExists(email);

        if (merchantEntity.isEmpty()) {
            LOGGER.error("Merchant not found by email {} specified in token, returning UNAUTHORIZED", email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Jwt token = jwtService.decode(authCookie);

        String name = token.getClaim("name").toString();

        String picture = token.getClaim("picture").toString();

        String exp = Objects.requireNonNull(token.getExpiresAt()).toString();

        String ref = merchantEntity.map(entity -> entity.getRef().toString()).orElse("");

        name = merchantEntity.map(MerchantEntity::getName).orElse(name);

        return ResponseEntity.status(HttpStatus.OK)
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
