package com.company.gmail.compliance.client;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.api.services.reports.Reports;
import com.google.api.services.reports.model.Activities;

@Component
public class AuditLogClient {

    private final Reports reports;

    public AuditLogClient(Reports reports) {
        this.reports = reports;
    }

    public Activities fetchLoginActivities() throws IOException {
        return reports.activities()
                .list("all", "login")
                .execute();
    }
}
