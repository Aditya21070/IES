package com.ies.plan_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ies.plan_service.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                // Admin only
                .requestMatchers(
                        HttpMethod.POST,
                        "/plans")
                .hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/plans/**")
                .hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.DELETE,
                        "/plans/**")
                .hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PATCH,
                        "/plans/**")
                .hasRole("ADMIN")

                // Everyone logged in can view
                .requestMatchers(
                        HttpMethod.GET,
                        "/plans/**")
                .authenticated()

                .anyRequest()
                .authenticated()

            )

            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}