package com.company.gmail.compliance.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.alertcenter.v1beta1.AlertCenter;
import com.google.api.services.directory.Directory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.licensing.Licensing;
import com.google.api.services.reports.Reports;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

@Configuration
public class GoogleApiConfig {

	@Value("${google.service-account.key-path}")
	private String keyPath;

	@Value("${google.workspace.admin-user}")
	private String adminUser;

	@Bean
	public GoogleCredentials googleCredentials() throws IOException {
		List<String> scopes = List.of("https://www.googleapis.com/auth/admin.directory.user.readonly",
				"https://www.googleapis.com/auth/admin.reports.usage.readonly",
				"https://www.googleapis.com/auth/admin.reports.audit.readonly",
				"https://www.googleapis.com/auth/admin.directory.user.security",
				"https://www.googleapis.com/auth/apps.alerts", "https://www.googleapis.com/auth/gmail.settings.basic",
				"https://www.googleapis.com/auth/gmail.settings.sharing",
				"https://www.googleapis.com/auth/apps.licensing"
				);

		// Ensure keyPath is not null or empty
		if (keyPath == null || keyPath.isEmpty()) {
			throw new IllegalArgumentException("Google Service Account Key path is missing in properties!");
		}

		System.out.println(keyPath);

		try (FileInputStream fis = new FileInputStream(keyPath)) {
			// Load from file - this automatically fills all required fields like Private
			// Key and ID
			GoogleCredentials credentials = GoogleCredentials.fromStream(fis).createScoped(scopes);

			// Explicitly handle Service Account Delegation
			if (credentials instanceof ServiceAccountCredentials) {
				return ((ServiceAccountCredentials) credentials).createDelegated(adminUser);
			}

			return credentials;
		}
	}

	@Bean
	public Directory directory(GoogleCredentials credentials) throws Exception {
		return new Directory.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(),
				new HttpCredentialsAdapter(credentials)).setApplicationName("gmail-compliance").build();
	}

	@Bean
	public Reports reports(GoogleCredentials credentials) throws Exception {
		return new Reports.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(),
				new HttpCredentialsAdapter(credentials)).setApplicationName("gmail-compliance").build();
	}

	@Bean
	public AlertCenter alertCenter(GoogleCredentials credentials) throws Exception {
		return new AlertCenter.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(),
				new HttpCredentialsAdapter(credentials)).setApplicationName("gmail-compliance").build();
	}

	@Bean
	public Gmail gmail(GoogleCredentials credentials) throws Exception {
		return new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(),
				new HttpCredentialsAdapter(credentials)).setApplicationName("gmail-compliance").build();
	}

	@Bean
	public Licensing licensing(GoogleCredentials credentials) throws Exception {
		return new Licensing.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(),
				new HttpCredentialsAdapter(credentials)).setApplicationName("gmail-compliance").build();
	}

}
