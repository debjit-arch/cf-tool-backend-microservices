package com.company.gmail.compliance.client;

import com.google.api.services.alertcenter.v1beta1.AlertCenter;
import com.google.api.services.alertcenter.v1beta1.model.Alert;
import com.google.api.services.alertcenter.v1beta1.model.ListAlertsResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlertCenterClient {

    private final AlertCenter alertCenter;

    public AlertCenterClient(AlertCenter alertCenter) {
        this.alertCenter = alertCenter;
    }

    /**
     * Fetches critical security alerts based on phishing and malware.
     * Filter syntax uses 'source' and 'type' fields.
     */
    public List<Alert> fetchSecurityAlerts() throws IOException {
        List<Alert> allAlerts = new ArrayList<>();
        String pageToken = null;

        // Correct filter for Phishing and Malware
        String filter = "source=\"Gmail phishing\" OR source=\"Google identity\"";

        do {
            ListAlertsResponse response = alertCenter.alerts().list()
                    .setFilter(filter)
                    .setPageSize(100)
                    .setPageToken(pageToken)
                    .execute();

            if (response.getAlerts() != null) {
                allAlerts.addAll(response.getAlerts());
            }
            pageToken = response.getNextPageToken();
        } while (pageToken != null);

        return allAlerts;
    }
}