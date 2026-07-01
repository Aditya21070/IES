package com.ies.citizen_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ies.citizen_service.filter.JwtAuthenticationFilter;

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

                        // Public endpoint (Citizen self registration)
                        .requestMatchers(HttpMethod.POST, "/citizens/register").permitAll()

                        .requestMatchers("/citizens/me")
                        .hasRole("CITIZEN")
                        
                        // Only Admin & Case Worker can register offline citizens
                        .requestMatchers(HttpMethod.POST, "/citizens")
                        .hasAnyRole("ADMIN", "CASE_WORKER")

                        // Only Admin & Case Worker can view citizens
                        .requestMatchers(HttpMethod.GET, "/citizens/**")
                        .hasAnyRole("ADMIN", "CASE_WORKER")

                        // Any authenticated user
                        .requestMatchers(
                                "/applications/**",
                                "/income-details/**",
                                "/education-details/**",
                                "/family-details/**",
                                "/kids/**",
                                "/bank-details/**"
                        ).authenticated()

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}