package com.example.demo_request.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_request.model.Demos;
import com.example.demo_request.service.DemoService;

@RestController
@RequestMapping("/api/demos")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    // POST: Create or Update
    @PostMapping
    public ResponseEntity<Demos> saveOrUpdate(@RequestBody Demos demo) {
        return ResponseEntity.ok(demoService.createOrUpdateDemo(demo));
    }

    // GET: Retrieve all demos
    @GetMapping
    public ResponseEntity<List<Demos>> getAllDemos() {
        return ResponseEntity.ok(demoService.getAllDemos());
    }

    // GET: Retrieve a single demo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Demos> getDemoById(@PathVariable String id) {
        return demoService.getDemoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}