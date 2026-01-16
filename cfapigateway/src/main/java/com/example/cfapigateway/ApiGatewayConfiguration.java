package com.example.cfapigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()

            // Example external API
            .route(p -> p.path("/get")
                .filters(f -> f.addRequestHeader("MyHeader", "MyURI")
                               .addRequestParameter("Param", "MyValues"))
                .uri("http://httpbin.org:80"))

            // --- Your Microservices ---
            .route("user-service", r -> r.path("/user-service/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://USER-SERVICE"))

            .route("risk-service", r -> r.path("/risk-service/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://RISK-SERVCE"))

            .route("task-service", r -> r.path("/task-service/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://TASK-SERVICE"))

            .route("docs-service", r -> r.path("/doc-service/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://DOCSERVICE"))

            .route("gap-service", r -> r.path("/gap-service/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://GAP-SERVICE"))

            .route("control-soa", r -> r.path("/control-soa/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://CONTROLSOA"))
            .route("email-service", r -> r.path("/email-service/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("lb://EMAIL-SERVICE"))
            .route("demo-service", r -> r.path("/demo-service/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("lb://DEMO-SERVICE"))
            .route("gws-service", r -> r.path("/gws-service/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("lb://GMAIL-COMPLIANCE"))
            .build();
    }
}

