package com.example.risk_service.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.risk_service.model.Risk;
import com.example.risk_service.repository.RiskRepository;

@RestController
@RequestMapping("/api/risks")
public class RiskController {

    @Autowired
    private RiskRepository riskRepository;

    // GET all risks
    @GetMapping
    public List<Risk> getAllRisks() {
        return riskRepository.findAll();
    }
    
    @GetMapping("/hello")
    public String HelloWorld() {
    	return("Hello World from Risk");
    }

    // GET risk by riskId
    @GetMapping("/{riskId}")
    public Risk getRiskByRiskId(@PathVariable String riskId) {
        return riskRepository.findByRiskId(riskId)
                .orElseThrow(() -> new RuntimeException("Risk not found"));
    }

    // POST create or update risk
    @PostMapping
    public Risk createOrUpdateRisk(@RequestBody Risk risk) {
        risk.setUpdatedAt(new Date());
        if (risk.getCreatedAt() == null) {
            risk.setCreatedAt(new Date());
        }

        Optional<Risk> existing = riskRepository.findByRiskId(risk.getRiskId());
        if (existing.isPresent()) {
            risk.setId(existing.get().getId());
        }
        return riskRepository.save(risk);
    }

    // DELETE risk by riskId
    @DeleteMapping("/{riskId}")
    public String deleteRisk(@PathVariable String riskId) {
        Optional<Risk> existing = riskRepository.findByRiskId(riskId);
        if (!existing.isPresent()) {
            return "Risk not found";
        }
        riskRepository.delete(existing.get());
        return "Risk deleted successfully";
    }
}
