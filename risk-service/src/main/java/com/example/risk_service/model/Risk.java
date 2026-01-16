package com.example.risk_service.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "risks")
public class Risk {
    @Id
    private String id;          // MongoDB ObjectId
    private String riskId;      // Your sequential or custom risk ID
    private String department;
    private String organization;
    private String date;
    private String riskType;
    private String assetType;
    private String asset;
    private String location;
    private String riskDescription;
    private Integer confidentiality;
    private Integer integrity;
    private Integer availability;
    private Integer impact;
    private String probability;
    private String threat;
    private List<String> vulnerability;
    private String existingControls;
    private String additionalNotes;
    private List<String> controlReference;
    private String additionalControls;
    private String numberOfDays;
    private String deadlineDate;
    private Integer riskScore;
    private String riskLevel;
    private String likelihoodAfterTreatment;
    private String impactAfterTreatment;
    private String status;
    private Date createdAt;
    private Date updatedAt;
	public Risk() {
		super();
	}
	public Risk(String id, String riskId, String organization,String department, String date, String riskType, String assetType,
			String asset, String location, String riskDescription, Integer confidentiality, Integer integrity,
			Integer availability, Integer impact, String probability, String threat, List<String> vulnerability,
			String existingControls, String additionalNotes, List<String> controlReference, String additionalControls,
			String numberOfDays, String deadlineDate, Integer riskScore, String riskLevel,
			String likelihoodAfterTreatment, String impactAfterTreatment, String status, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.riskId = riskId;
		this.organization=organization;
		this.department = department;
		this.date = date;
		this.riskType = riskType;
		this.assetType = assetType;
		this.asset = asset;
		this.location = location;
		this.riskDescription = riskDescription;
		this.confidentiality = confidentiality;
		this.integrity = integrity;
		this.availability = availability;
		this.impact = impact;
		this.probability = probability;
		this.threat = threat;
		this.vulnerability = vulnerability;
		this.existingControls = existingControls;
		this.additionalNotes = additionalNotes;
		this.controlReference = controlReference;
		this.additionalControls = additionalControls;
		this.numberOfDays = numberOfDays;
		this.deadlineDate = deadlineDate;
		this.riskScore = riskScore;
		this.riskLevel = riskLevel;
		this.likelihoodAfterTreatment = likelihoodAfterTreatment;
		this.impactAfterTreatment = impactAfterTreatment;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRiskDescription() {
		return riskDescription;
	}
	public void setRiskDescription(String riskDescription) {
		this.riskDescription = riskDescription;
	}
	public Integer getConfidentiality() {
		return confidentiality;
	}
	public void setConfidentiality(Integer confidentiality) {
		this.confidentiality = confidentiality;
	}
	public Integer getIntegrity() {
		return integrity;
	}
	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public Integer getImpact() {
		return impact;
	}
	public void setImpact(Integer impact) {
		this.impact = impact;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getThreat() {
		return threat;
	}
	public void setThreat(String threat) {
		this.threat = threat;
	}
	public List<String> getVulnerability() {
		return vulnerability;
	}
	public void setVulnerability(List<String> vulnerability) {
		this.vulnerability = vulnerability;
	}
	public String getExistingControls() {
		return existingControls;
	}
	public void setExistingControls(String existingControls) {
		this.existingControls = existingControls;
	}
	public String getAdditionalNotes() {
		return additionalNotes;
	}
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	public List<String> getControlReference() {
		return controlReference;
	}
	public void setControlReference(List<String> controlReference) {
		this.controlReference = controlReference;
	}
	public String getAdditionalControls() {
		return additionalControls;
	}
	public void setAdditionalControls(String additionalControls) {
		this.additionalControls = additionalControls;
	}
	public String getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public String getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	public Integer getRiskScore() {
		return riskScore;
	}
	public void setRiskScore(Integer riskScore) {
		this.riskScore = riskScore;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getLikelihoodAfterTreatment() {
		return likelihoodAfterTreatment;
	}
	public void setLikelihoodAfterTreatment(String likelihoodAfterTreatment) {
		this.likelihoodAfterTreatment = likelihoodAfterTreatment;
	}
	public String getImpactAfterTreatment() {
		return impactAfterTreatment;
	}
	public void setImpactAfterTreatment(String impactAfterTreatment) {
		this.impactAfterTreatment = impactAfterTreatment;
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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	@Override
	public String toString() {
		return "Risk [id=" + id + ", riskId=" + riskId + ", department=" + department + ", organization=" + organization
				+ ", date=" + date + ", riskType=" + riskType + ", assetType=" + assetType + ", asset=" + asset
				+ ", location=" + location + ", riskDescription=" + riskDescription + ", confidentiality="
				+ confidentiality + ", integrity=" + integrity + ", availability=" + availability + ", impact=" + impact
				+ ", probability=" + probability + ", threat=" + threat + ", vulnerability=" + vulnerability
				+ ", existingControls=" + existingControls + ", additionalNotes=" + additionalNotes
				+ ", controlReference=" + controlReference + ", additionalControls=" + additionalControls
				+ ", numberOfDays=" + numberOfDays + ", deadlineDate=" + deadlineDate + ", riskScore=" + riskScore
				+ ", riskLevel=" + riskLevel + ", likelihoodAfterTreatment=" + likelihoodAfterTreatment
				+ ", impactAfterTreatment=" + impactAfterTreatment + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
    
    
}
