package com.example.controlsoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsoa.model.Control;
import com.example.controlsoa.service.ControlService;

@RestController
@RequestMapping("/api/controls")
public class ControlController {

	@Autowired
	private ControlService controlService;

	@GetMapping
	public List<Control> getAllControls() {
		return controlService.getAllControls();
	}

	@PostMapping
	public Control createControl(@RequestBody Control control) {
		return controlService.createControl(control);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteControl(@PathVariable int id) {
		boolean deleted = controlService.deleteControl(id);
		if (!deleted) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build(); // 204 No Content
	}

}
