package com.example.gap_service.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class UploadConfig {

    @Value("${uploads.dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
