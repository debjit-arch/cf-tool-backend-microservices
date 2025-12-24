package com.example.user_service.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "departments")
public class Department {
    @Id
    private String id;
    private String name;
    private String organization;
	public Department() {
		super();
	}
	public Department(String id, String name, String organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
    
}
