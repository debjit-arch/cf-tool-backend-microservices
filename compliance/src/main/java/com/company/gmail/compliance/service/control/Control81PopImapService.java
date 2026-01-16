package com.company.gmail.compliance.service.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.PopImapClient;
import com.company.gmail.compliance.client.ReportsClient;
import com.company.gmail.compliance.model.ControlResult;
import com.company.gmail.compliance.model.UserMfaDetail;

@Service
public class Control81PopImapService {

    private final ReportsClient reportsClient;
    private final PopImapClient popImapClient;

    public Control81PopImapService(ReportsClient reportsClient, PopImapClient popImapClient) {
        this.reportsClient = reportsClient;
        this.popImapClient = popImapClient;
    }

    public ControlResult run() throws IOException {
        List<ReportsClient.UserMfaStatus> users = reportsClient.getAllUsersMfaStatus();
        List<UserMfaDetail> details = new ArrayList<>();
        boolean allDisabled = true;

        for (var user : users) {
            try {
                var status = popImapClient.getUserAccessStatus(user.email());
                
                // COMPLIANCE RULE: POP and IMAP must both be FALSE (Disabled)
                boolean isUserCompliant = !status.popEnabled() && !status.imapEnabled();
                
                if (!isUserCompliant) {
                    allDisabled = false;
                }
                details.add(new UserMfaDetail(user.email(), isUserCompliant));

            } catch (Exception e) {
                // IMPORTANT: If we hit a 403 or 404, we mark as non-compliant 
                // but DON'T crash the whole application.
                allDisabled = false;
                details.add(new UserMfaDetail(user.email(), false));
                System.err.println("Skipping user " + user.email() + " due to: " + e.getMessage());
            }
        }

        return new ControlResult("8.1", "POP/IMAP Access Disabled", allDisabled, details);
    }
}