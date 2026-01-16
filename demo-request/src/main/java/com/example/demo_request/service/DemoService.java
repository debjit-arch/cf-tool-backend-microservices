package com.example.demo_request.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo_request.model.Demos;
import com.example.demo_request.repository.DemoRepository;

@Service
public class DemoService {

	private final DemoRepository repo;

	public DemoService(DemoRepository repo) {
		this.repo = repo;
	}

	public Demos createOrUpdateDemo(Demos request) {
		// Look for existing entry by organization
		Optional<Demos> existing = repo.findByOrganization(request.getOrganization());

		if (existing.isPresent()) {
			Demos demo = existing.get();

			// Update only the fields available in your Model
			if (request.getName() != null)
				demo.setName(request.getName());
			if (request.getEmail() != null)
				demo.setEmail(request.getEmail());
			if (request.getEmployee() != null)
				demo.setEmployee(request.getEmployee());
			if (request.getPhone() != null)
				demo.setPhone(request.getPhone());
			// Organization is already matching, but we set it for clarity
			if (request.getOrganization() != null)
				demo.setOrganization(request.getOrganization());

			return repo.save(demo);
		}

		// If no existing organization found, create new record
		return repo.save(request);
	}

	public List<Demos> getAllDemos() {
		return repo.findAll();
	}

	// New method to get by ID
	public Optional<Demos> getDemoById(String id) {
		return repo.findById(id);
	}
}