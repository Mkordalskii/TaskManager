package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Wyłączamy CSRF na potrzeby testów
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable()) // Wyłączamy ochronę ramek dla H2
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // H2 Console dostępne bez uwierzytelnienia
                        .anyRequest().authenticated() // Wszystkie inne endpointy wymagają autoryzacji
                )
                .formLogin(Customizer.withDefaults()) // Domyślna konfiguracja logowania formularzowego
                .httpBasic(Customizer.withDefaults()) // Domyślna konfiguracja Basic Auth
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Kodowanie haseł za pomocą BCrypt
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
