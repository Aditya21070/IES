package com.ies.payment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ies.payment_service.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // Health Check
                .requestMatchers("/actuator/**")
                    .permitAll()

                // Payment APIs
                .requestMatchers(HttpMethod.POST, "/payments/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                .requestMatchers(HttpMethod.GET, "/payments/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                .requestMatchers(HttpMethod.PUT, "/payments/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                .requestMatchers(HttpMethod.POST, "/payments/*/pay")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")
                    
                .requestMatchers(HttpMethod.DELETE, "/payments/**")
                    .hasRole("ADMIN")

                .anyRequest()
                    .authenticated()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}