package com.company.gmail.compliance.service.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.VaultLicenseClient;
import com.company.gmail.compliance.model.ControlResult;
import com.company.gmail.compliance.model.UserMfaDetail;
import com.google.api.services.directory.model.User;

@Service
public class Control813VaultLicenseService {

    private static final String CONTROL_ID = "8.13";
    private static final String CONTROL_NAME =
            "Vault License & Retention (Email Recoverability)";

    private final VaultLicenseClient vaultClient;

    public Control813VaultLicenseService(VaultLicenseClient vaultClient) {
        this.vaultClient = vaultClient;
    }

    public ControlResult run() throws IOException {

        List<UserMfaDetail> nonCompliantUsers = new ArrayList<>();

        // 1️⃣ Fetch all users (Directory API)
        List<User> users = vaultClient.fetchAllUsers();

        for (User user : users) {

            String email = user.getPrimaryEmail();

            // 2️⃣ Check Vault license via Licensing API
            boolean hasVault = vaultClient.userHasVaultLicense(email);

            if (!hasVault) {
                nonCompliantUsers.add(
                    new UserMfaDetail(email, false)
                );
            }
        }

        boolean compliant = nonCompliantUsers.isEmpty();

        return new ControlResult(
                CONTROL_ID,
                CONTROL_NAME,
                compliant,
                nonCompliantUsers
        );
    }
}
