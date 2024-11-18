package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration // Indicates that this class contains Spring configuration.
public class SecurityConfig {

    /**
     * Configures the security settings for the application.
     * @param http The HttpSecurity object for configuring security.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disables CSRF for simplicity (not recommended for production).
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/register").permitAll() // Allows public access to these endpoints.
                        .requestMatchers("/tasks/**").hasAnyRole("USER", "ADMIN") // Access for users with 'USER' or 'ADMIN' roles.
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Access for users with 'ADMIN' role.
                        .anyRequest().authenticated() // All other requests require authentication.
                )
                .httpBasic(httpBasic -> {}) // Enables Basic Authentication.
                .formLogin(form -> form.defaultSuccessUrl("/tasks", true)); // Enables form-based login.

        return http.build();
    }

    /**
     * Provides a password encoder for securing passwords.
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an authentication manager for handling user authentication.
     * @param authConfig The authentication configuration.
     * @return The AuthenticationManager instance.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
