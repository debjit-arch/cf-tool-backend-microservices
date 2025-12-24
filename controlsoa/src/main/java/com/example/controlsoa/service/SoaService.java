package com.example.controlsoa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controlsoa.model.Soa;
import com.example.controlsoa.repository.SoaRepository;

@Service
public class SoaService {

    @Autowired
    private SoaRepository soaRepo;

    public List<Soa> getAllSoa() {
        return soaRepo.findAll();
    }

    public Soa createSoa(Soa soa) {
        soa.setId(System.currentTimeMillis());
        soa.setCreatedAt(new Date());
        soa.setUpdatedAt(new Date());
        if (soa.getStatus() == null) soa.setStatus("Planned");
        return soaRepo.save(soa);
    }

    public Soa updateSoa(long id, Soa updatedSoa) {
        Soa soa = soaRepo.findById(id);
        if (soa == null) return null;

        updatedSoa.setUpdatedAt(new Date());
        updatedSoa.setId(soa.getId());
        return soaRepo.save(updatedSoa);
    }

    public boolean deleteSoa(long id) {
        Soa soa = soaRepo.findById(id);
        if (soa == null) return false;

        soaRepo.delete(soa);
        return true;
    }
}
