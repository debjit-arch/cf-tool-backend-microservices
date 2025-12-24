package com.example.task_service.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;           // MongoDB ObjectId
    private String taskId;       // T-1, T-2, ...
    private String riskId;       // Reference to risk
    private String department;
    private String organization;
    private String employee;     // Assigned employee
    private String description;
    private String startDate;
    private String endDate;
    private String status;       // Pending, Completed
    private Date createdAt;
    private Date updatedAt;
	public Task() {
		super();
	}
	public Task(String id, String taskId, String riskId, String organization,String department, String employee, String description,
			String startDate, String endDate, String status, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.riskId = riskId;
		this.organization=organization;
		this.department = department;
		this.employee = employee;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public String getId() {
		return id;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskId=" + taskId + ", riskId=" + riskId + ", department=" + department
				+ ", organization=" + organization + ", employee=" + employee + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}   
}
