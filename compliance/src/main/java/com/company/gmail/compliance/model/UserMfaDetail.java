package com.company.gmail.compliance.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="usermfadetail")
public class UserMfaDetail {
    private String email;
    private boolean mfaEnabled;

    public UserMfaDetail(String email, boolean mfaEnabled) {
        this.email = email;
        this.mfaEnabled = mfaEnabled;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMfaEnabled() {
		return mfaEnabled;
	}

	public void setMfaEnabled(boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}

	@Override
	public String toString() {
		return "UserMfaDetail [email=" + email + ", mfaEnabled=" + mfaEnabled + "]";
	}
    
}
