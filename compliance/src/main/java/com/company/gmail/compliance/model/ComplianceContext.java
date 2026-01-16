package com.company.gmail.compliance.model;

public class ComplianceContext {

    private final String domain;
    private final String customerId;

    public ComplianceContext(String domain, String customerId) {
        this.domain = domain;
        this.customerId = customerId;
    }

    public String getDomain() {
        return domain;
    }

    public String getCustomerId() {
        return customerId;
    }
}
