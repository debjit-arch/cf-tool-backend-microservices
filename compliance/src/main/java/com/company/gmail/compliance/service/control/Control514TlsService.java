package com.company.gmail.compliance.service.control;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.AdminSettingsClient;
import com.company.gmail.compliance.model.ControlResult;

@Service
public class Control514TlsService {

    private final AdminSettingsClient adminSettingsClient;

    public Control514TlsService(AdminSettingsClient adminSettingsClient) {
        this.adminSettingsClient = adminSettingsClient;
    }

    public ControlResult run(String customerId) {
        try {
            boolean enforced = adminSettingsClient.isTlsEnforced(customerId);

            String description = enforced
                    ? "TLS Enforcement enabled for outbound email"
                    : "TLS Enforcement is NOT enabled for outbound email";

            return new ControlResult("5.14", description, enforced,null);

        } catch (Exception e) {
            return new ControlResult(
                    "5.14",
                    "TLS Enforcement check failed: " + e.getMessage(),
                    false,
                    null
            );
        }
    }
}
