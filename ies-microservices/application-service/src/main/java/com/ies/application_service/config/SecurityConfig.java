package com.ies.application_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ies.application_service.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // Health Check
                .requestMatchers("/actuator/**").permitAll()
                
                .requestMatchers("/applications/dashboard/**")
                .hasAnyRole("ADMIN", "CASE_WORKER")
                
                .requestMatchers(
                        HttpMethod.PUT,
                        "/applications/internal/**")
                    .permitAll()

                // -------------------------
                // Application APIs
                // -------------------------
                .requestMatchers(HttpMethod.POST, "/applications/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                .requestMatchers(HttpMethod.PUT, "/applications/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                .requestMatchers(HttpMethod.GET, "/applications/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                // -------------------------
                // Income APIs
                // -------------------------
                .requestMatchers("/income-details/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                // -------------------------
                // Education APIs
                // -------------------------
                .requestMatchers("/education-details/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                // -------------------------
                // Family APIs
                // -------------------------
                .requestMatchers("/family-details/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                // -------------------------
                // Kids APIs
                // -------------------------
                .requestMatchers("/kids/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                // -------------------------
                // Bank APIs
                // -------------------------
                .requestMatchers("/bank-details/**")
                    .hasAnyRole("CITIZEN", "CASE_WORKER", "ADMIN")

                // Any other request must be authenticated
                .anyRequest().authenticated()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}