package com.company.gmail.compliance.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.gmail.compliance.model.ComplianceContext;
import com.company.gmail.compliance.model.ComplianceResult;
import com.company.gmail.compliance.model.ControlResult;
import com.company.gmail.compliance.service.control.Control514DnsService;
import com.company.gmail.compliance.service.control.Control514TlsService;
import com.company.gmail.compliance.service.control.Control51AcceptableUseService;
import com.company.gmail.compliance.service.control.Control812AutoForwardingService;
import com.company.gmail.compliance.service.control.Control813VaultLicenseService;
import com.company.gmail.compliance.service.control.Control815AuditLogService;
import com.company.gmail.compliance.service.control.Control81PopImapService;
import com.company.gmail.compliance.service.control.Control85LegacyLoginService;
import com.company.gmail.compliance.service.control.Control85MfaService;

@Service
public class ComplianceRunnerService {

	private final Control85MfaService mfaService;
	private final Control514DnsService dnsService;
	private final Control85LegacyLoginService oldService;
	private final Control812AutoForwardingService autoForwardingService;
	private final Control51AcceptableUseService acceptService;
	private final Control514TlsService tlsService;
	private final Control81PopImapService popService;
	private final Control815AuditLogService auditService;
	//private final Control813VaultLicenseService vaultService;

	public ComplianceRunnerService(Control85MfaService mfaService, Control514DnsService dnsService,
			Control85LegacyLoginService oldService, Control812AutoForwardingService autoForwardingService,
			Control51AcceptableUseService acceptService, Control514TlsService tlsService,
			Control81PopImapService popService, Control815AuditLogService auditService,
			Control813VaultLicenseService vaultService) {

		this.mfaService = mfaService;
		this.dnsService = dnsService;
		this.oldService = oldService;
		this.acceptService = acceptService;
		this.autoForwardingService = autoForwardingService;
		this.tlsService = tlsService;
		this.popService = popService;
		this.auditService = auditService;
		//this.vaultService = vaultService;

	}

	public ComplianceResult runAll(ComplianceContext context) {

		try {
			// Run the check only when this method is called
			ControlResult mfaResult = mfaService.run();
			ControlResult dnsResult = dnsService.run(context.getDomain());
			ControlResult oldResult = oldService.run();
			ControlResult acceptResult = acceptService.run();
			ControlResult autoForwardingResult = autoForwardingService.run();
			ControlResult tlsResult = tlsService.run(context.getCustomerId());
			ControlResult popResult = popService.run();
			ControlResult auditResult = auditService.run();
			//ControlResult vaultResult = vaultService.run();
			return new ComplianceResult(List.of(mfaResult, dnsResult, oldResult, acceptResult, autoForwardingResult,
					tlsResult, popResult, auditResult//,vaultResult
					));
		} catch (IOException e) {
			// Handle the error gracefully if the API call fails
			throw new RuntimeException("API Communication error during compliance run", e);
		}
	}
}
