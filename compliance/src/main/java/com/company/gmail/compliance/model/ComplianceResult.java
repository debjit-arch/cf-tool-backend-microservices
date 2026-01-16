package com.company.gmail.compliance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "compresult")
@Data
public class ComplianceResult {

    @Id
    private String id;   // Mongo document ID

    private List<ControlResult> results;

	public ComplianceResult(List<ControlResult> results) {
		super();
		this.results = results;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ControlResult> getResults() {
		return results;
	}

	public void setResults(List<ControlResult> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ComplianceResult [id=" + id + ", results=" + results + "]";
	}
}
