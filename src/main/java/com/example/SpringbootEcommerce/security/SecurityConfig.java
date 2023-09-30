package com.example.SpringbootEcommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import java.net.http.HttpRequest;

@Configuration
public class SecurityConfig {
    @Autowired
    private JWTFilter jwtFilter;
    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).cors(cors->cors.disable());
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        //http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/product/getproducts","/auth/login","/auth/register").permitAll().anyRequest().authenticated());
        return http.build();
    }
}
