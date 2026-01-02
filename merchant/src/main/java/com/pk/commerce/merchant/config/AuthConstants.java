package com.pk.commerce.merchant.config;

public class AuthConstants {
    public static final String AUTH_COOKIE_NAME = "Auth";
    public static final String[] EXCLUDE_AUTH_PATTERNS = {
            "/", "/login/", "/logout",
            "/error",
            "/static/**",
            "/oauth_login",
            "/api/auth/login",
            "/api/auth/logout",
            "/api/orders/**",
            "/login/oauth2/code/google"};
}
