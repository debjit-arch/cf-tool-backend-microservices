package com.company.gmail.compliance.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.gmail.compliance.model.ComplianceContext;
import com.company.gmail.compliance.service.ComplianceRunnerService;

@Component
public class ComplianceScheduler {

    private final ComplianceRunnerService runnerService;
    private final String domain;
    private final String customerId;

    public ComplianceScheduler(
            ComplianceRunnerService runnerService,
            @Value("${compliance.primary-domain}") String domain,
            @Value("${google.workspace.customer-id}") String customerId) {
    	

        this.runnerService = runnerService;
        this.domain = domain;
        this.customerId=customerId;
    }

    /**
     * Nightly compliance run at 2 AM
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void nightlyRun() {
    	ComplianceContext context = new ComplianceContext(domain,customerId);
        runnerService.runAll(context);
    }
}
