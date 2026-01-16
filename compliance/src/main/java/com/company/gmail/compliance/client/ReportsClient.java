package com.company.gmail.compliance.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.api.services.directory.Directory;
import com.google.api.services.directory.model.User;
import com.google.api.services.directory.model.Users;

@Component
public class ReportsClient {

    private final Directory directory;

    public ReportsClient(Directory directory) {
        this.directory = directory;
    }

    public List<UserMfaStatus> getAllUsersMfaStatus() throws IOException {
    List<UserMfaStatus> result = new ArrayList<>();
    String pageToken = null;

    do {
        // Build the request inside the loop to ensure the token is updated correctly
        Directory.Users.List request = directory.users().list()
                .setCustomer("my_customer")
                .setMaxResults(500)
                .setOrderBy("email")
                .setPageToken(pageToken);

        Users users = request.execute();
        
        if (users.getUsers() != null) {
            for (User user : users.getUsers()) {
                boolean mfaEnabled = user.getIsEnrolledIn2Sv() != null && user.getIsEnrolledIn2Sv();
                result.add(new UserMfaStatus(user.getPrimaryEmail(), mfaEnabled));
            }
        }
        pageToken = users.getNextPageToken();
    } while (pageToken != null);

    return result;
}

    public boolean allUsersHaveMfa() throws IOException {
        return getAllUsersMfaStatus().stream().allMatch(UserMfaStatus::mfaEnabled);
    }

    public record UserMfaStatus(String email, boolean mfaEnabled) {}
}
