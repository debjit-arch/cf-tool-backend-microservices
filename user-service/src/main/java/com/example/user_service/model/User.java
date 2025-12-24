package com.example.user_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String oldPassword;
    public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	private String role; // super_admin, risk_owner, risk_manager, risk_identifier

    // Replace @DBRef with departmentId
    private String department;

    // Optional fields
    private boolean isAuditor = false; // true if this user is an auditor
    private String auditorName; // optional name for auditor display

    private Date createdAt;
    private Date updatedAt;

    private String organization;
    // Constructors
    public User() {}

    public User(String id, String name, String email, String password, String role,
                String organization, String department, boolean isAuditor, String auditorName,
                Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.organization = organization;
        this.department = department;
        this.isAuditor = isAuditor;
        this.auditorName = auditorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isAuditor() {
		return isAuditor;
	}

	public void setAuditor(boolean isAuditor) {
		this.isAuditor = isAuditor;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
