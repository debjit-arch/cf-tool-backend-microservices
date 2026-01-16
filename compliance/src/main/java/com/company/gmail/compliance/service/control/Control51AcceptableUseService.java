package com.company.gmail.compliance.service.control;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.AlertCenterClient;
import com.company.gmail.compliance.model.ControlResult;
import com.company.gmail.compliance.model.UserMfaDetail;
import com.google.api.services.alertcenter.v1beta1.model.Alert;

@Service
public class Control51AcceptableUseService {

    private final AlertCenterClient alertClient;

    public Control51AcceptableUseService(AlertCenterClient alertClient) {
        this.alertClient = alertClient;
    }

    public ControlResult run() throws IOException {
        List<Alert> alerts = alertClient.fetchSecurityAlerts();

        // Compliance Logic: Are there any "NOT_STARTED" or "IN_PROGRESS" alerts?
        boolean compliant = alerts.stream()
                .noneMatch(a -> "HIGH".equalsIgnoreCase(a.getMetadata().getSeverity()) 
                             && !"CLOSED".equalsIgnoreCase(a.getMetadata().getStatus()));

        // Map alerts to a detail list for the report
        List<UserMfaDetail> details = alerts.stream()
                .map(a -> new UserMfaDetail(
                        a.getAlertId() + ": " + a.getType(), 
                        "CLOSED".equalsIgnoreCase(a.getMetadata().getStatus())
                ))
                .collect(Collectors.toList());

        return new ControlResult(
            "8.3", 
            "Acceptable use of Information", 
            compliant, 
            details
        );
    }
}