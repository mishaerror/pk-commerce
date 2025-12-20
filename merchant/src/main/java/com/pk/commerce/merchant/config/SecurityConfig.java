package com.pk.commerce.merchant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/", "/login**", "/error", "/static/**", "/oauth**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(
                        oauth -> oauth
                                .loginPage("/oauth_login")
                                .defaultSuccessUrl("/merchants/welcome", true)
                )
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
