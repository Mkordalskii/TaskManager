package com.example.taskmanager.config;

import com.example.taskmanager.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Wyłączenie CSRF dla testów
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // H2 Console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/register").permitAll() // Endpointy publiczne
                        .anyRequest().authenticated() // Wszystkie inne endpointy wymagają logowania
                )
                .formLogin(form -> form.defaultSuccessUrl("/tasks", true)) // Logowanie formularzowe z przekierowaniem
                .httpBasic(httpBasic -> {}) // Uwierzytelnianie Basic Auth
                .build();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Kodowanie hasła
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
