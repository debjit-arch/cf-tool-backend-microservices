package com.company.gmail.compliance.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "ctrlresult")
@Data
public class ControlResult {

    @Id
    private String id;          // Mongo document ID

    private String controlId;
    private String controlName;
    private boolean compliant;
    private List<UserMfaDetail> userDetails;
    public ControlResult(String controlId, String controlName, boolean compliant, List<UserMfaDetail> userDetails) {
        this.controlId = controlId;
        this.controlName = controlName;
        this.compliant = compliant;
        this.userDetails = userDetails;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public String getControlName() {
		return controlName;
	}
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	public boolean isCompliant() {
		return compliant;
	}
	public void setCompliant(boolean compliant) {
		this.compliant = compliant;
	}
	
	public List<UserMfaDetail> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(List<UserMfaDetail> userDetails) {
		this.userDetails = userDetails;
	}
	@Override
	public String toString() {
		return "ControlResult [id=" + id + ", controlId=" + controlId + ", controlName=" + controlName + ", compliant="
				+ compliant + ", userDetails=" + userDetails + "]";
	}
}
