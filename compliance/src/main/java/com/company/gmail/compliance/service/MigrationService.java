package com.company.gmail.compliance.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.services.directory.Directory;
import com.google.api.services.directory.model.User;
import com.google.api.services.reports.Reports;
import com.google.api.services.reports.model.Activity;

@Service
public class MigrationService {

    private final Directory directoryService;
    private final Reports reportsService;

    public MigrationService(Directory directoryService, Reports reportsService) {
        this.directoryService = directoryService;
        this.reportsService = reportsService;
    }
    
    public boolean checkMfaCompliance(List<String> nonCompliantUsers) {
        try {
            // Fetch users via Directory API
            List<User> allUsers = directoryService.users().list()
                    .setCustomer("my_customer")
                    .execute()
                    .getUsers();

            if (allUsers == null) return true;

            for (User user : allUsers) {
                String email = user.getPrimaryEmail();

                // Check login activities via Reports API
                Reports.Activities.List request = reportsService.activities()
                        .list("all", "login")
                        .setUserKey(email)
                        .setEventName("2sv_enrollment") // Common event name for 2FA
                        .setMaxResults(1);

                List<Activity> activities = request.execute().getItems();
                if (activities == null || activities.isEmpty()) {
                    nonCompliantUsers.add(email);
                }
            }
            return nonCompliantUsers.isEmpty();
        } catch (IOException e) {
            throw new RuntimeException("Compliance check failed", e);
        }
    }
}