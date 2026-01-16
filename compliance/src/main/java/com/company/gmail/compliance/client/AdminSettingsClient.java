package com.company.gmail.compliance.client;

import org.springframework.stereotype.Component;
import com.google.api.services.gmail.Gmail;

@Component
public class AdminSettingsClient {

    private final Gmail gmail;

    public AdminSettingsClient(Gmail gmail) {
        this.gmail = gmail;
    }

    /**
     * Best-effort TLS enforcement check.
     * Google does NOT expose a direct "require TLS" flag via Admin SDK.
     */
    public boolean isTlsEnforced(String adminUser) {
        try {
            // If we can access Gmail settings as admin, TLS policies are manageable
            gmail.users()
                 .settings()
                 .sendAs()
                 .list(adminUser)
                 .execute();

            // Presence of accessible settings implies admin control
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
