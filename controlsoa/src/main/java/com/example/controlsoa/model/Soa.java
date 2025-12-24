package com.example.controlsoa.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "soa")
public class Soa {
    @Id
    private String _id;
    private Long id; // using Date.now() style
    private Integer controlId;
    private String category;
    private String description;
    private String status;
    private List<String> documentRef;
    private String justification;
    private Date createdAt;
    private Date updatedAt;
    private String organization;
	public Soa() {
		super();
	}
	public Soa(String _id, Long id, Integer controlId, String category, String description, String status,
			List<String> documentRef, String justification, Date createdAt, Date updatedAt,String organization) {
		super();
		this._id = _id;
		this.id = id;
		this.controlId = controlId;
		this.category = category;
		this.description = description;
		this.status = status;
		this.documentRef = documentRef;
		this.justification = justification;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.organization = organization;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getControlId() {
		return controlId;
	}
	public void setControlId(Integer controlId) {
		this.controlId = controlId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getDocumentRef() {
		return documentRef;
	}
	public void setDocumentRef(List<String> documentRef) {
		this.documentRef = documentRef;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
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
	@Override
	public String toString() {
		return "Soa [_id=" + _id + ", id=" + id + ", controlId=" + controlId + ", category=" + category
				+ ", description=" + description + ", status=" + status + ", documentRef=" + documentRef
				+ ", justification=" + justification + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
    
}
