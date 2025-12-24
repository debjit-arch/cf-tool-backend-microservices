package com.example.user_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import lombok.RequiredArgsConstructor; // not needed if not using constructor injection

@Configuration
// @RequiredArgsConstructor // not needed if using @Autowired
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // ------------------ REMOVE CORS FILTER ------------------
    // @Bean
    // public CorsFilter corsFilter() {
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.setAllowCredentials(true);
    //     config.setAllowedOrigins(List.of("http://localhost:3000")); // NOT needed, gateway handles CORS
    //     config.setAllowedHeaders(List.of("*"));
    //     config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", config);
    //     return new CorsFilter(source);
    // }

    // Security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable CSRF for REST APIs
        http.csrf(csrf -> csrf.disable());

        // Enable CORS (gateway will handle actual CORS headers)
        http.cors();

        // Stateless session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Authorization rules
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // allow preflight requests
            .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll() // login POST is public
            .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() // register POST is public
            .requestMatchers(HttpMethod.GET, "/api/users/**")
                .hasAnyRole("SUPER_ADMIN", "RISK_OWNER", "RISK_MANAGER", "RISK_IDENTIFIER")
            .requestMatchers(HttpMethod.POST, "/api/users/**")
                .hasAnyRole("SUPER_ADMIN", "RISK_OWNER", "RISK_MANAGER")
            .anyRequest().authenticated()
        );

        // Add JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
