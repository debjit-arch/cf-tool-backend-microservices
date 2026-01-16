package com.company.gmail.compliance.client;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ImapSettings;
import com.google.api.services.gmail.model.PopSettings;

@Component
public class PopImapClient {

    private final Gmail gmail;

    public PopImapClient(Gmail gmail) {
        this.gmail = gmail;
    }

    public UserAccessStatus getUserAccessStatus(String email) throws IOException {
        try {
            ImapSettings imap = gmail.users().settings().getImap(email).execute();
            PopSettings pop = gmail.users().settings().getPop(email).execute();
            
            boolean imapEnabled = Boolean.TRUE.equals(imap.getEnabled());
            boolean popEnabled = pop.getDisposition() != null && !"disabled".equalsIgnoreCase(pop.getDisposition());
            
            return new UserAccessStatus(email, popEnabled, imapEnabled);
        } catch (com.google.api.client.googleapis.json.GoogleJsonResponseException e) {
            if (e.getStatusCode() == 403) {
                // Log it once and return 'true' (Enabled/Risk) because we cannot verify it is disabled
                System.err.println("COMPLIANCE WARNING: Cannot verify " + email + " due to delegation restrictions.");
                return new UserAccessStatus(email, true, true); 
            }
            throw e;
        }
    }

    public record UserAccessStatus(String email, boolean popEnabled, boolean imapEnabled) {}
}