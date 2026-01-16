package com.company.gmail.compliance.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.company.gmail.compliance.model.UserMfaDetail;
import com.google.api.services.directory.Directory;
import com.google.api.services.directory.model.User;
import com.google.api.services.directory.model.Users;

@Component
public class LegacyLoginClient {

    private final Directory directory;

    public LegacyLoginClient(Directory directory) {
        this.directory = directory;
    }

    public List<UserMfaDetail> fetchLegacyLoginStatus() throws IOException {
        List<UserMfaDetail> results = new ArrayList<>();
        String pageToken = null;

        do {
            Users users = directory.users().list()
                    .setCustomer("my_customer")
                    .setMaxResults(500)
                    .setPageToken(pageToken)
                    .execute();

            if (users.getUsers() != null) {
                for (User user : users.getUsers()) {

                    Boolean enrolled = user.getIsEnrolledIn2Sv();
                    Boolean enforced = user.getIsEnforcedIn2Sv();

                    boolean legacyLoginBlocked =
                            Boolean.TRUE.equals(enrolled)
                            && Boolean.TRUE.equals(enforced);

                    results.add(
                        new UserMfaDetail(
                            user.getPrimaryEmail(),
                            legacyLoginBlocked
                        )
                    );
                }
            }

            pageToken = users.getNextPageToken();
        } while (pageToken != null);

        return results;
    }
}
