package com.example.risk_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable()) // disable CSRF for REST APIs
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/risks/hello").permitAll() // public endpoint
	            .requestMatchers("/api/risks/**").permitAll() // all others need auth
	        )
	        .httpBasic(); // Basic Auth or JWT filter if added

	    http.cors(); // enable CORS, handled by gateway

	    return http.build();
	}


	// CORS configuration source for Security
	// @Bean
	// public CorsConfigurationSource corsConfigurationSource() {
//      CorsConfiguration config = new CorsConfiguration();
//      config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // frontend origin
//      config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
//      config.setAllowedHeaders(Arrays.asList("*"));
//      config.setAllowCredentials(true); // allow Authorization header
	//
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/**", config);
//      return source;
	// }

}
