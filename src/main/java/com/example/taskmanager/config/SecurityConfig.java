package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Wyłączamy CSRF na potrzeby testów
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Dostęp bez autoryzacji do H2
                        .anyRequest().authenticated() // Wymagane uwierzytelnienie dla pozostałych endpointów
                )
                .formLogin(Customizer.withDefaults()) // Domyślna konfiguracja logowania formularzowego
                .httpBasic(Customizer.withDefaults()) // Domyślna konfiguracja Basic Auth
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Kodowanie haseł za pomocą BCrypt
    }
}
