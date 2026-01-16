package com.company.gmail.compliance.service.control;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.LegacyLoginClient;
import com.company.gmail.compliance.model.ControlResult;
import com.company.gmail.compliance.model.UserMfaDetail;

@Service
public class Control85LegacyLoginService {

    private final LegacyLoginClient client;

    public Control85LegacyLoginService(LegacyLoginClient client) {
        this.client = client;
    }

    public ControlResult run() {
        try {
            List<UserMfaDetail> users = client.fetchLegacyLoginStatus();

            List<UserMfaDetail> nonCompliant =
                    users.stream()
                         .filter(u -> !u.isMfaEnabled())
                         .toList();

            boolean compliant = nonCompliant.isEmpty();

            String controlName = compliant
                    ? "Legacy login blocked for all users"
                    : "Legacy login allowed for " + nonCompliant.size() + " users";

            return new ControlResult(
                    "8.5",
                    controlName,
                    compliant,
                    nonCompliant
            );

        } catch (Exception e) {
            return new ControlResult(
                    "8.5",
                    "Legacy login check failed: " + e.getMessage(),
                    false,
                    List.of()
            );
        }
    }
}
