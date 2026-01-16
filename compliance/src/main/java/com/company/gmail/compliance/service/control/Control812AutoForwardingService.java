package com.company.gmail.compliance.service.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.AutoForwardingClient;
import com.company.gmail.compliance.client.ReportsClient; // To get the user list
import com.company.gmail.compliance.model.ControlResult;
import com.company.gmail.compliance.model.UserMfaDetail;

@Service
public class Control812AutoForwardingService {

    private final AutoForwardingClient autoForwardingClient;
    private final ReportsClient reportsClient; // Existing client to list users

    public Control812AutoForwardingService(AutoForwardingClient autoForwardingClient, ReportsClient reportsClient) {
        this.autoForwardingClient = autoForwardingClient;
        this.reportsClient = reportsClient;
    }

    public ControlResult run() {
        List<UserMfaDetail> offenders = new ArrayList<>();

        try {
            // 1. Get all users from Directory (reusing your ReportsClient logic)
            List<ReportsClient.UserMfaStatus> users = reportsClient.getAllUsersMfaStatus();

            // 2. Check each user's LIVE Gmail settings
            for (var user : users) {
                UserMfaDetail detail = autoForwardingClient.checkUserForwarding(user.email());
                
                // If isMfaEnabled is used as a proxy for "Is Compliant"
                // in your model, we add it if it is NOT compliant (forwarding is ON)
                if (detail.isMfaEnabled()) { // In our logic: true = forwarding is active (non-compliant)
                    offenders.add(detail);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to run Auto-Forwarding compliance check", e);
        }

        boolean compliant = offenders.isEmpty();

        String description = compliant
                ? "No users have automatic email forwarding enabled"
                : "Automatic forwarding enabled for " + offenders.size() + " users";

        return new ControlResult(
                "8.12",
                description,
                compliant,
                offenders
        );
    }
}