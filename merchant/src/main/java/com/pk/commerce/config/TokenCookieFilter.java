package com.pk.commerce.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.pk.commerce.config.AuthConstants.AUTH_COOKIE_NAME;

@Component
public class TokenCookieFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final List<PathPatternRequestMatcher> matchers = Arrays.stream(AuthConstants.EXCLUDE_AUTH_PATTERNS)
            .map(
                    path -> PathPatternRequestMatcher.withDefaults().matcher(path)).toList();

    private final JwtService jwtService;

    public TokenCookieFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String requestUri = request.getRequestURI();
        for (PathPatternRequestMatcher matcher : matchers) {
            if (matcher.matches(request)) {
                LOGGER.info("Skipping filtering of url {}", requestUri);
                return true;
            }
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (AUTH_COOKIE_NAME.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            LOGGER.error("Token not found in cookie or header of uri={}, returning UNAUTHORIZED.", request.getRequestURI());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {

            try {
                if (jwtService.decode(token) != null) {
                    String username = jwtService.decode(token).getSubject();

                    var auth = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.emptyList()
                    );

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (JwtValidationException e) {
                LOGGER.error("Exception parsing the token", e);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }

        filterChain.doFilter(request, response);
    }
}