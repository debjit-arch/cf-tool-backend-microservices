package com.company.gmail.compliance.model;

public class UserLegacyLoginDetails {

    private String email;
    private boolean legacyLoginAllowed;

    public UserLegacyLoginDetails(String email, boolean legacyLoginAllowed) {
        this.email = email;
        this.legacyLoginAllowed = legacyLoginAllowed;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLegacyLoginAllowed() {
        return legacyLoginAllowed;
    }

    @Override
    public String toString() {
        return "UserLegacyLoginDetail [email=" + email +
               ", legacyLoginAllowed=" + legacyLoginAllowed + "]";
    }
}
