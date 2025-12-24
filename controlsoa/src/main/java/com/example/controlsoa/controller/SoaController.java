package com.example.controlsoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsoa.model.Soa;
import com.example.controlsoa.service.SoaService;

@RestController
@RequestMapping("/api/soa")
public class SoaController {

    @Autowired
    private SoaService soaService;

    @GetMapping
    public List<Soa> getAllSoa() {
        return soaService.getAllSoa();
    }

    @PostMapping
    public Soa createSoa(@RequestBody Soa soa) {
        return soaService.createSoa(soa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Soa> updateSoa(@PathVariable long id, @RequestBody Soa soa) {
        Soa updated = soaService.updateSoa(id, soa);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSoa(@PathVariable long id) {
        boolean deleted = soaService.deleteSoa(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body("SoA entry deleted");
    }
}
