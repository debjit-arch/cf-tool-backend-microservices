package com.company.gmail.compliance.service.control;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.ReportsClient;
import com.company.gmail.compliance.model.ControlResult;
import com.company.gmail.compliance.model.UserMfaDetail;

@Service
public class Control85MfaService {

    private final ReportsClient reportsClient;

    public Control85MfaService(ReportsClient reportsClient) {
        this.reportsClient = reportsClient;
    }

    public ControlResult run() throws IOException {
        List<ReportsClient.UserMfaStatus> statuses = reportsClient.getAllUsersMfaStatus();
        boolean compliant = statuses.stream().allMatch(ReportsClient.UserMfaStatus::mfaEnabled);

        List<UserMfaDetail> userDetails = statuses.stream()
                .map(u -> new UserMfaDetail(u.email(), u.mfaEnabled()))
                .collect(Collectors.toList());

        return new ControlResult("8.5", "MFA Enforcement", compliant, userDetails);
    }
}
