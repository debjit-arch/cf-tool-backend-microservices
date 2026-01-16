package com.company.gmail.compliance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "evidence")
@Data
public class Evidence {

    @Id
    private String id;   // Mongo IDs are typically String (ObjectId)

    private String controlId;

    private String rawJson;  // No @Lob needed in Mongo

    private Instant collectedAt;

	public Evidence(String controlId, String rawJson, Instant collectedAt) {
		super();
		this.controlId = controlId;
		this.rawJson = rawJson;
		this.collectedAt = collectedAt;
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

	public String getRawJson() {
		return rawJson;
	}

	public void setRawJson(String rawJson) {
		this.rawJson = rawJson;
	}

	public Instant getCollectedAt() {
		return collectedAt;
	}

	public void setCollectedAt(Instant collectedAt) {
		this.collectedAt = collectedAt;
	}

	@Override
	public String toString() {
		return "Evidence [id=" + id + ", controlId=" + controlId + ", rawJson=" + rawJson + ", collectedAt="
				+ collectedAt + "]";
	}
    
    
}
