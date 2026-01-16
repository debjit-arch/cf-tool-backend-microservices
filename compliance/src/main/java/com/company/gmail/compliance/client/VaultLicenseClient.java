package com.company.gmail.compliance.client;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.api.services.directory.Directory;
import com.google.api.services.directory.model.User;
import com.google.api.services.licensing.Licensing;
import com.google.api.services.licensing.model.LicenseAssignment;

@Component
public class VaultLicenseClient {

    private final Licensing licensing;
    private final Directory directory;

    // Product ID is constant for Google Workspace
    private static final String PRODUCT_ID = "Google-Apps";

    // Vault-capable SKUs
    private static final Set<String> VAULT_SKUS = Set.of(
            "Google-Vault",
            "Enterprise",
            "Enterprise-Standard",
            "Enterprise-Plus"
    );

    public VaultLicenseClient(Licensing licensing, Directory directory) {
        this.licensing = licensing;
        this.directory = directory;
    }

    /**
     * Checks whether a user has ANY Vault-capable license.
     */
    public boolean userHasVaultLicense(String userEmail) {
        for (String skuId : VAULT_SKUS) {
            try {
                LicenseAssignment assignment =
                        licensing.licenseAssignments()
                                .get(PRODUCT_ID, skuId, userEmail)
                                .execute();

                if (assignment != null) {
                    return true;
                }

            } catch (com.google.api.client.googleapis.json.GoogleJsonResponseException e) {
                // 404 = user does not have this SKU → expected
                if (e.getStatusCode() != 404) {
                    throw new RuntimeException(
                        "Failed checking Vault license for " + userEmail, e);
                }
            } catch (IOException e) {
                throw new RuntimeException(
                    "Failed checking Vault license for " + userEmail, e);
            }
        }
        return false;
    }

    /**
     * Fetch all users from Directory API
     */
    public List<User> fetchAllUsers() throws IOException {
        return directory.users().list()
                .setCustomer("my_customer")
                .setMaxResults(500)
                .execute()
                .getUsers();
    }
}
