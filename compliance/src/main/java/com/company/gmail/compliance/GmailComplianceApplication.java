package com.company.gmail.compliance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GmailComplianceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailComplianceApplication.class, args);
    }
}
