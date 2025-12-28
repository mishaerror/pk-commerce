package com.pk.commerce.merchant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final Oauth2AuthenticationSuccessHandler authenticationSuccessHandler;
    private final TokenCookieOrHeaderFilter tokenCookieOrHeaderFilter;
    private final MerchantRefFilter merchantRefFilter;

    @Value("${custom.security.oauth2.redirect-url}")
    private String redirectUrl;

    public SecurityConfig(Oauth2AuthenticationSuccessHandler authenticationSuccessHandler, TokenCookieOrHeaderFilter tokenCookieOrHeaderFilter, MerchantRefFilter merchantRefFilter) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.tokenCookieOrHeaderFilter = tokenCookieOrHeaderFilter;
        this.merchantRefFilter = merchantRefFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(AuthConstants.EXCLUDE_AUTH_PATTERNS).permitAll()
                                .anyRequest().authenticated()
                ).logout(l -> l
                        .deleteCookies(AuthConstants.AUTH_COOKIE_NAME))
                .oauth2Login(
                        oauth -> oauth
                                .defaultSuccessUrl(redirectUrl, true)
                                .successHandler(authenticationSuccessHandler)
                ).sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(tokenCookieOrHeaderFilter, OAuth2LoginAuthenticationFilter.class)
                .addFilterAfter(merchantRefFilter, TokenCookieOrHeaderFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setExposedHeaders(List.of("Set-Cookie", "Cookie"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply this configuration to all paths
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
