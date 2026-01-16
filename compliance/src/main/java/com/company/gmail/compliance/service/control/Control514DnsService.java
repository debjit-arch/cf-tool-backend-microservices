package com.company.gmail.compliance.service.control;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.client.DnsClient;
import com.company.gmail.compliance.model.ControlResult;

@Service
public class Control514DnsService {

    private final DnsClient dnsClient;

    public Control514DnsService(DnsClient dnsClient) {
        this.dnsClient = dnsClient;
    }

    public ControlResult run(String domain) {
        try {
            boolean spf = hasSpf(domain);
            boolean dmarc = hasDmarc(domain);
            boolean dkim = hasDkim(domain);

            boolean compliant = spf && dmarc && dkim;

            String desc = "DNS Security - SPF: " + spf +
                          ", DKIM: " + dkim +
                          ", DMARC: " + dmarc;

            return new ControlResult("5.14", desc, compliant, null);

        } catch (Exception e) {
            return new ControlResult(
                "5.14",
                "DNS check failed: " + e.getMessage(),
                false, null
            );
        }
    }

    private boolean hasSpf(String domain) throws Exception {
        return dnsClient.getTxtRecords(domain)
                .stream().anyMatch(r -> r.contains("v=spf1"));
    }

    private boolean hasDmarc(String domain) throws Exception {
        return dnsClient.getTxtRecords("_dmarc." + domain)
                .stream().anyMatch(r -> r.contains("v=DMARC1"));
    }

    private boolean hasDkim(String domain) throws Exception {
        return dnsClient.getTxtRecords("google._domainkey." + domain)
                .stream().anyMatch(r -> r.contains("v=DKIM1"));
    }
}
