package com.pk.commerce.merchant.rest.api;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public String authMeResponse() {
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<?, ?> attributes = principal.getAttributes();
        /**
         * "at_hash" -> "7vhtGW0ADtc06ODka8YVag"
         * "sub" -> "112613431163524510435"
         * "email_verified" -> {Boolean@11814} true
         * "iss" -> {URL@11816} "https://accounts.google.com"
         * "given_name" -> "Miloš"
         * "nonce" -> "fzJB5gXcpQk2rVRKEWS4qH8wNxhiAuNVfJg1mgpREI8"
         * "picture" -> "https://lh3.googleusercontent.com/a/ACg8ocK9X7OS-d5OjslWaN-bWKrT4oATnS-eltQgppWHETmnZzvfCw=s96-c"
         * "aud" -> {ArrayList@11824}  size = 1
         * "azp" -> "939916800727-pdmvqks4go07af20cf9d3vnodu4i8721.apps.googleusercontent.com"
         * "name" -> "Miloš Stoiljković"
         * "exp" -> {Instant@11767} "2025-12-22T21:27:03Z"
         * "family_name" -> "Stoiljković"
         * "iat" -> {Instant@11766} "2025-12-22T20:27:03Z"
         * "email" -> "mishaerror@gmail.com"
         */
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        String picture = (String) attributes.get("picture");
        Instant exp = (Instant) attributes.get("exp");

        return """
                 {
                  "id": "123",
                  "name": "%s",
                  "email": "%s",
                  "exp": "%s",
                  "picture": "%s"
                  }
                """.formatted(name, email, exp, picture);
    }
}
