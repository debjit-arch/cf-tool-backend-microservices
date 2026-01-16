package com.company.gmail.compliance.service.control;

import java.io.IOException;
import java.util.Collections;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.AuditLogClient;
import com.company.gmail.compliance.model.ControlResult;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.reports.model.Activities;

@Service
public class Control815AuditLogService {

    private static final String CONTROL_ID = "8.15";
    private static final String CONTROL_NAME = "Audit Log Activity (Login Events)";

    private final AuditLogClient auditLogClient;

    public Control815AuditLogService(AuditLogClient auditLogClient) {
        this.auditLogClient = auditLogClient;
    }

    public ControlResult run() throws IOException {
        try {
            Activities activities = auditLogClient.fetchLoginActivities();

            boolean hasEvents =
                    activities != null
                    && activities.getItems() != null
                    && !activities.getItems().isEmpty();

            return new ControlResult(
                    CONTROL_ID,
                    CONTROL_NAME,
                    true,                 // logging is active
                    Collections.emptyList()
            );

        } catch (GoogleJsonResponseException e) {

            // 403 means logging is disabled or access blocked
            if (e.getStatusCode() == 403) {
                return new ControlResult(
                        CONTROL_ID,
                        CONTROL_NAME,
                        false,
                        Collections.emptyList()
                );
            }

            throw e; // real error
        }
    }
}
