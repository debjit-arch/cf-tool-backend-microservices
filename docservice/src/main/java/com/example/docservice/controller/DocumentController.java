package com.example.docservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.docservice.model.Doc;
import com.example.docservice.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

	@Autowired
	private DocumentService service;

	@GetMapping
	public List<Doc> getDocuments(@RequestParam(required = false) String controlId,
			@RequestParam(required = false) String soaId) {
		return service.getDocuments(controlId, soaId);
	}

	@PostMapping("/upload")
	public Doc upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String soaId,
			@RequestParam(required = false) String controlId, @RequestParam(required = false) String uploaderName,
			@RequestParam(required = false) String departmentName, @RequestParam(required = false) String organization)
			throws Exception {
		return service.upload(file, soaId, controlId, uploaderName, departmentName, organization);
	}

	@PatchMapping("/{id}/approval")
	public ResponseEntity<?> approve(@PathVariable Long id, @RequestBody Map<String, Object> body) {
		if (!body.containsKey("approvalDate"))
			return ResponseEntity.badRequest().body("Approval date required");

		Object value = body.get("approvalDate");

		long timestamp;
		try {
			timestamp = Long.parseLong(value.toString());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid approvalDate format");
		}

		Date approvalDate = new Date(timestamp);

		Doc updated = service.updateApproval(id, approvalDate);
		if (updated == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		boolean deleted = service.delete(id);
		if (!deleted)
			return ResponseEntity.notFound().build();

		return ResponseEntity.noContent().build();
	}
}
