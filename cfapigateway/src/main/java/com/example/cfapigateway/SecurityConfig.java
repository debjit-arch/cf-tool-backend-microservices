package com.example.cfapigateway;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.csrf(csrf -> csrf.disable()).authorizeExchange(ex -> ex.pathMatchers(HttpMethod.OPTIONS).permitAll() // preflight
				.anyExchange().permitAll()).headers(headers -> headers.frameOptions(frame -> frame.disable())).cors(); // enable
																														// CORS

		return http.build();
	}

	// This CORS filter ensures headers are added before security
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOriginPatterns(Arrays.asList("https://*.calvant.com", "https://calvant.com",
				"https://www.calvant.com", "http://localhost:3000", "https://pre-prod.d1iwz64jvqpior.amplifyapp.com",
				"https://tool.consultantsfactory.com"));

		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}

}
