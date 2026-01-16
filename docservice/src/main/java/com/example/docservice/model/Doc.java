package com.example.docservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "documents")
public class Doc {

    @Id
    private Long id;

    private String name;
    private String url;
    private String soaId;
    private String controlId;
    private String uploaderName;
    private String departmentName;
    private Date approvalDate;
    private Date nextApprovalDate;
    private Date createdAt = new Date();
    private String organization;
	public Doc() {
		super();
	}
	public Doc(Long id, String name, String url, String soaId, String controlId, String uploaderName,
			String departmentName, Date approvalDate, Date nextApprovalDate, Date createdAt,String organization) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.soaId = soaId;
		this.controlId = controlId;
		this.uploaderName = uploaderName;
		this.departmentName = departmentName;
		this.approvalDate = approvalDate;
		this.nextApprovalDate = nextApprovalDate;
		this.createdAt = createdAt;
		this.organization = organization;
	}
	public Long getId() {
		return id;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSoaId() {
		return soaId;
	}
	public void setSoaId(String soaId) {
		this.soaId = soaId;
	}
	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public String getUploaderName() {
		return uploaderName;
	}
	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public Date getNextApprovalDate() {
		return nextApprovalDate;
	}
	public void setNextApprovalDate(Date nextApprovalDate) {
		this.nextApprovalDate = nextApprovalDate;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Doc [id=" + id + ", name=" + name + ", url=" + url + ", soaId=" + soaId + ", controlId=" + controlId
				+ ", uploaderName=" + uploaderName + ", departmentName=" + departmentName + ", approvalDate="
				+ approvalDate + ", nextApprovalDate=" + nextApprovalDate + ", createdAt=" + createdAt
				+ ", organization=" + organization + "]";
	}
    
    
}
