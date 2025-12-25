package com.pk.commerce.merchant.config;

import com.pk.commerce.merchant.domain.admin.MerchantRegistrationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

@Component
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${custom.security.oauth2.redirect-url}")
    private String redirectUrl;

    private final MerchantRegistrationService merchantRegistrationService;

    private final JwtService jwtService;

    public Oauth2AuthenticationSuccessHandler(MerchantRegistrationService merchantRegistrationService, JwtService jwtService) {
        this.merchantRegistrationService = merchantRegistrationService;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        assert token.getPrincipal() != null;

        String email = token.getPrincipal().getAttribute("email");
        String name = token.getPrincipal().getAttribute("name");
        String picture = token.getPrincipal().getAttribute("picture");

        boolean merchantExists = merchantRegistrationService.merchantEmailExists(email).isPresent();

        String authToken = jwtService.generateToken(email, name, picture, merchantExists ? "register" : "").getTokenValue();

        Cookie authCookie = new Cookie("Auth", authToken);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setDomain("localhost");
        authCookie.setPath("/");
        authCookie.setAttribute("SameSite", "lax");
        authCookie.setMaxAge(60 * 10);
        response.addCookie(authCookie);



        String params = "?Auth=" + authToken;
        if (!merchantExists) {
            params += "&flow=REGISTER";
            merchantRegistrationService.addMerchantRegistrationRequest(name, null, null, email, null, null);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl + params);
    }
}
