package com.example.demo_request.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "demos")
public class Demos {

	@Id
	private String id;
	private String name;
	private String email;
	private String organization;
	private String employee;
	private String phone;
	public Demos() {
		super();
	}
	public Demos(String id, String name, String email, String organization, String employee, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.organization = organization;
		this.employee = employee;
		this.phone = phone;
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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Demos [id=" + id + ", name=" + name + ", email=" + email + ", organization=" + organization
				+ ", employee=" + employee + ", phone=" + phone + "]";
	}
	
	
}
