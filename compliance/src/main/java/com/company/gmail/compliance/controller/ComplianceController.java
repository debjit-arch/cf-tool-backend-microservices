package com.company.gmail.compliance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.gmail.compliance.model.ComplianceContext;
import com.company.gmail.compliance.model.ComplianceResult;
import com.company.gmail.compliance.repository.ComplianceResultRepository;
import com.company.gmail.compliance.service.ComplianceRunnerService;

@RestController
@RequestMapping("/compliance")
public class ComplianceController {

    private final ComplianceRunnerService runnerService;
    private final ComplianceResultRepository repository;

    public ComplianceController(
            ComplianceRunnerService runnerService,
            ComplianceResultRepository repository) {

        this.runnerService = runnerService;
        this.repository = repository;
    }

    @GetMapping("/run")
    public ComplianceResult run(
            @RequestParam String domain,
            @RequestParam String customerId) {

        ComplianceContext context =
                new ComplianceContext(domain, customerId);

        return runnerService.runAll(context);
    }
}
