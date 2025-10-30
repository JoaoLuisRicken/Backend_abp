package com.backend.jjj.cinema_api.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/users/admin").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/users/employee").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/users/account").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/users").authenticated()
                .requestMatchers(HttpMethod.POST, "/rooms").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .requestMatchers(HttpMethod.PATCH, "/rooms/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/seats").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .requestMatchers(HttpMethod.PATCH, "/seats/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/sessions").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .requestMatchers("/tickets", "/tickets/**").authenticated()
                .requestMatchers(HttpMethod.POST,"/movies").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .requestMatchers(HttpMethod.PATCH,"/movies/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .anyRequest().permitAll()
        );

        // Filtro JWT
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
