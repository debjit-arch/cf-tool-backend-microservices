package com.example.docservice.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.docservice.model.Doc;
import com.example.docservice.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {

	@Autowired
	private DocumentRepository repo;

	@Value("${uploads.dir}")
	private String uploadDir;

	public List<Doc> getDocuments(String controlId, String soaId) {
		if (controlId != null)
			return repo.findByControlId(controlId);
		if (soaId != null)
			return repo.findBySoaId(soaId);
		return repo.findAll();
	}

	public Doc upload(MultipartFile file, String soaId, String controlId, String uploaderName, String departmentName,
			String organization) throws Exception {

		long id = System.currentTimeMillis();
		String uniqueName = id + "-" + file.getOriginalFilename();

		Path uploadPath = Path.of(uploadDir);
		if (!Files.exists(uploadPath))
			Files.createDirectories(uploadPath);

		file.transferTo(uploadPath.resolve(uniqueName).toFile());

		Doc doc = new Doc();
		doc.setId(id);
		doc.setName(file.getOriginalFilename());
		doc.setUrl("/uploads/" + uniqueName);
		doc.setSoaId(soaId);
		doc.setControlId(controlId);
		doc.setUploaderName(Optional.ofNullable(uploaderName).orElse("Unknown"));
		doc.setDepartmentName(Optional.ofNullable(departmentName).orElse("N/A"));
		doc.setOrganization(Optional.ofNullable(organization).orElse("N/A")); // ✅ set organization
		doc.setCreatedAt(new Date());

		return repo.save(doc);
	}

	public Doc updateApproval(Long id, Date approvalDate) {
		Optional<Doc> opt = repo.findById(id);
		if (opt.isEmpty())
			return null;

		Doc doc = opt.get();
		doc.setApprovalDate(approvalDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(approvalDate);
		cal.add(Calendar.DATE, 365);
		doc.setNextApprovalDate(cal.getTime());

		return repo.save(doc);
	}

	public boolean delete(Long id) {
		Optional<Doc> opt = repo.findById(id);
		if (opt.isEmpty())
			return false;

		Doc doc = opt.get();

		File file = new File(uploadDir, new File(doc.getUrl()).getName());
		if (file.exists())
			file.delete();

		repo.delete(doc);
		return true;
	}
}
