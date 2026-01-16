package com.company.gmail.compliance.client;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.AutoForwarding;
import com.company.gmail.compliance.model.UserMfaDetail;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AutoForwardingClient {

    private final Gmail gmail;

    public AutoForwardingClient(Gmail gmail) {
        this.gmail = gmail;
    }

    /**
     * Checks if a specific user has auto-forwarding enabled.
     * @param userEmail The email of the user to check (requires Domain-Wide Delegation)
     */
    public UserMfaDetail checkUserForwarding(String userEmail) {
        try {
            // Real-time check via Gmail API settings
            AutoForwarding forwarding = gmail.users().settings()
                    .getAutoForwarding(userEmail)
                    .execute();

            boolean isEnabled = forwarding != null && Boolean.TRUE.equals(forwarding.getEnabled());
            
            // Return the detail object (mapping isEnabled to your model logic)
            return new UserMfaDetail(userEmail, isEnabled);

        } catch (IOException e) {
            // 403 usually means the user hasn't logged into Gmail yet or delegation is missing
            System.err.println("Could not fetch forwarding for " + userEmail + ": " + e.getMessage());
            return new UserMfaDetail(userEmail, false);
        }
    }
}