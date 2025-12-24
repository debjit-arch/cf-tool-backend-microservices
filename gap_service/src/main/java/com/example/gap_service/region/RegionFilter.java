package com.example.gap_service.region;

import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RegionFilter extends OncePerRequestFilter {

    private static final Set<String> ALLOWED = Set.of("in", "eu", "us");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String region = request.getHeader("x-region");

        if (region == null || !ALLOWED.contains(region.toLowerCase())) {
            region = "in"; // default
        }

        RegionContext.set(region.toLowerCase());

        try {
            filterChain.doFilter(request, response);
        } finally {
            RegionContext.clear();
        }
    }
}
