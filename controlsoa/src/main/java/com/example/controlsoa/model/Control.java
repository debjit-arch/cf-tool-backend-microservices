package com.example.controlsoa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "controls")
public class Control {
    @Id
    private String _id; // MongoDB ObjectId
    private Integer id;
    private String category;
    private String description;
    private String organization;
	public Control() {
		super();
	}
	public Control(String _id, Integer id, String category, String description,String organization) {
		super();
		this._id = _id;
		this.id = id;
		this.category = category;
		this.description = description;
		this.organization = organization;
	}
	public String get_id() {
		return _id;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Control [_id=" + _id + ", id=" + id + ", category=" + category + ", description=" + description
				+ ", organization=" + organization + "]";
	}
    
    
}
