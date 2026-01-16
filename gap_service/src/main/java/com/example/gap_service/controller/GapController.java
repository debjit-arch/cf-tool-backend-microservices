package com.example.gap_service.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.gap_service.model.Gaps;
import com.example.gap_service.repository.GapRepository;
import com.example.gap_service.service.GapService;

@RestController
@RequestMapping("/api/gaps")
public class GapController {

	@Autowired
	private GapRepository repo;
	@Autowired
	private GapService service;

	@Value("${uploads.dir}")
	private String uploadDir;

	public GapController(GapRepository repo, GapService service) {
		this.repo = repo;
		this.service = service;
	}

	@GetMapping("/hello")
	public String helloGap() {
		return "Hello from Gap Service";
	}

	// ----------------------- FILE UPLOAD -----------------------
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {

		if (file.isEmpty())
			return ResponseEntity.badRequest().body(Map.of("message", "No file uploaded"));

		String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
		Path filePath = Path.of(uploadDir, fileName);

		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return ResponseEntity.ok(Map.of("url", "/uploads/" + fileName));
	}

	// ----------------------- CREATE/UPDATE GAP -----------------------
	@PostMapping
	public ResponseEntity<?> createOrUpdate(@RequestBody Gaps gap) {
		System.out.println("Received gap: " + gap);
		return ResponseEntity.ok(service.createOrUpdateGap(gap));
	}

	// ----------------------- GET ALL GAPS -----------------------
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(repo.findAll());
	}

	// ----------------------- AUDITOR UPDATE -----------------------
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody Map<String, Object> body) {

		return repo.findById(id).map(gap -> {
			gap.setDocScore((String) body.get("docScore"));
			gap.setPracticeScore((String) body.get("practiceScore"));
			gap.setDocRemarks((String) body.get("docRemarks"));
			gap.setPracticeRemarks((String) body.get("practiceRemarks"));
			gap.setVerifiedBy((String) body.get("verifiedBy"));
			gap.setStatus("Verified");

			return ResponseEntity.ok(repo.save(gap));
		}).orElse(ResponseEntity.notFound().build());
	}

	// ----------------------- DELETE FILE BY URL -----------------------
	@DeleteMapping("/by-url")
	public ResponseEntity<?> deleteByUrl(@RequestBody Map<String, String> body) {

		String url = body.get("url");
		String field = body.get("field");

		if (url == null || field == null)
			return ResponseEntity.badRequest().body(Map.of("error", "File URL and field are required"));

		if (!field.equals("documentURL") && !field.equals("practiceEvidence"))
			return ResponseEntity.badRequest().body(Map.of("error", "Invalid field"));

		return repo.findByDocumentURL(url).or(() -> repo.findByPracticeEvidence(url)).map(gap -> {

			File file = new File(uploadDir, Path.of(url).getFileName().toString());
			if (file.exists())
				file.delete();

			if (field.equals("documentURL"))
				gap.setDocumentURL(null);
			if (field.equals("practiceEvidence"))
				gap.setPracticeEvidence(null);

			repo.save(gap);

			return ResponseEntity.ok(Map.of("message", "Document deleted successfully"));
		}).orElse(ResponseEntity.status(404).body(Map.of("error", "Document not found")));
	}
}
