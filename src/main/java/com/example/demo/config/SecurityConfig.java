package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Autoriser l'accès public aux pages suivantes
                .requestMatchers("/", "/products", "/product/*","/increase-quantity/*","/remove-from-cart/**","/change-language","/confirm-order","/order-confirmation","/checkout", "/cart", "/add-to-cart/*").permitAll()
                // Autoriser l'accès public aux ressources statiques
                .requestMatchers("/images/*", "/css", "/js/*").permitAll()
                // Autoriser l'accès public aux endpoints API
                .requestMatchers("/products/api/batch**").permitAll()
                // Autoriser l'accès à la console H2 (pour le développement)
                .requestMatchers("/h2-console/**").permitAll()
                // Toutes les autres routes nécessitent une authentification
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()) // Désactiver complètement CSRF pour tester
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin()) // Autoriser les iframes pour la console H2
            );

        return http.build();
    }
}