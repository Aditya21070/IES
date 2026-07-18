package com.ies.eligibility_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {

            RequestAttributes attributes =
                    RequestContextHolder.getRequestAttributes();

            if (attributes instanceof ServletRequestAttributes servlet) {

                HttpServletRequest request =
                        servlet.getRequest();

                String auth =
                        request.getHeader("Authorization");

                if (auth != null && !auth.isBlank()) {

                    template.header("Authorization", auth);
                }
            }
        };
    }
}