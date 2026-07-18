package com.ies.application_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig {

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//
//        return requestTemplate -> {
//
//            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
//
//            if (attributes instanceof ServletRequestAttributes servletAttributes) {
//
//                HttpServletRequest request = servletAttributes.getRequest();
//
//                String authorization = request.getHeader("Authorization");
//
//                if (authorization != null && !authorization.isBlank()) {
//                    requestTemplate.header("Authorization", authorization);
//                }
//            }
//        };
//    }
	
	@Bean
	public RequestInterceptor requestInterceptor() {

	    return requestTemplate -> {

	        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

	        if (attributes instanceof ServletRequestAttributes servletAttributes) {

	            HttpServletRequest request = servletAttributes.getRequest();

	            String authorization = request.getHeader("Authorization");

	            System.out.println("========== FEIGN ==========");
	            System.out.println("Authorization = " + authorization);

	            if (authorization != null && !authorization.isBlank()) {
	                requestTemplate.header("Authorization", authorization);
	            }
	        }
	    };
	}
}