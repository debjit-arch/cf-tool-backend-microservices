package com.example.gap_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "gaps")
public class Gaps {

	@Id
	private String id;

	private String clause;
	private String standardRequirement;
	private String question;

	private String documentURL;
	private String practiceEvidence;

	private String docScore;
	private String practiceScore;

	private String docRemarks;
	private String practiceRemarks;
	
	private String createdBy;
	private String verifiedBy;
	private String department; // simple string, e.g., "IT" or "HR"

	private String status = "Pending";

    // ---------------- NEW FIELDS ----------------
    private Integer totalScore;        // sum of docScore + practiceScore
    private String practiceNotes;      // free text notes
    private String documentNotes;
    private String organization;
    

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Gaps() {
		super();
	}

	public Gaps(String id, String clause, String standardRequirement, String question, String documentURL,
			String practiceEvidence, String docScore, String practiceScore, String docRemarks, String practiceRemarks,
			String createdBy, String verifiedBy, String department, String status,Integer totalScore,String practiceNotes,String documentNotes,String organization) {
		super();
		this.id = id;
		this.clause = clause;
		this.standardRequirement = standardRequirement;
		this.question = question;
		this.documentURL = documentURL;
		this.practiceEvidence = practiceEvidence;
		this.docScore = docScore;
		this.practiceScore = practiceScore;
		this.docRemarks = docRemarks;
		this.practiceRemarks = practiceRemarks;
		this.createdBy = createdBy;
		this.verifiedBy = verifiedBy;
		this.department = department;
		this.status = status;
		this.totalScore=totalScore;
		this.practiceNotes=practiceNotes; 
		this.documentNotes=documentNotes;
		this.organization = organization;
	}

	public String getId() {
		return id;
	}

	public String getDocumentNotes() {
		return documentNotes;
	}

	public void setDocumentNotes(String documentNotes) {
		this.documentNotes = documentNotes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public String getStandardRequirement() {
		return standardRequirement;
	}

	public void setStandardRequirement(String standardRequirement) {
		this.standardRequirement = standardRequirement;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDocumentURL() {
		return documentURL;
	}

	public void setDocumentURL(String documentURL) {
		this.documentURL = documentURL;
	}

	public String getPracticeEvidence() {
		return practiceEvidence;
	}

	public void setPracticeEvidence(String practiceEvidence) {
		this.practiceEvidence = practiceEvidence;
	}

	public String getDocScore() {
		return docScore;
	}

	public void setDocScore(String docScore) {
		this.docScore = docScore;
	}

	public String getPracticeScore() {
		return practiceScore;
	}

	public void setPracticeScore(String practiceScore) {
		this.practiceScore = practiceScore;
	}

	public String getDocRemarks() {
		return docRemarks;
	}

	public void setDocRemarks(String docRemarks) {
		this.docRemarks = docRemarks;
	}

	public String getPracticeRemarks() {
		return practiceRemarks;
	}

	public void setPracticeRemarks(String practiceRemarks) {
		this.practiceRemarks = practiceRemarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getPracticeNotes() {
		return practiceNotes;
	}

	public void setPracticeNotes(String practiceNotes) {
		this.practiceNotes = practiceNotes;
	}

	@Override
	public String toString() {
		return "Gaps [id=" + id + ", clause=" + clause + ", standardRequirement=" + standardRequirement + ", question="
				+ question + ", documentURL=" + documentURL + ", practiceEvidence=" + practiceEvidence + ", docScore="
				+ docScore + ", practiceScore=" + practiceScore + ", docRemarks=" + docRemarks + ", practiceRemarks="
				+ practiceRemarks + ", createdBy=" + createdBy + ", verifiedBy=" + verifiedBy + ", department="
				+ department + ", status=" + status + ", totalScore=" + totalScore + ", practiceNotes=" + practiceNotes
				+ ", documentNotes=" + documentNotes + ", organization=" + organization + "]";
	}

}
