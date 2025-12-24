package com.example.controlsoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controlsoa.model.Control;
import com.example.controlsoa.model.Soa;
import com.example.controlsoa.repository.ControlRepository;
import com.example.controlsoa.repository.SoaRepository;

@Service
public class ControlService {

    @Autowired
    private ControlRepository controlRepo;

    @Autowired
    private SoaRepository soaRepo;

    public List<Control> getAllControls() {
        return controlRepo.findAll();
    }

    public Control createControl(Control control) {
        Integer newId = controlRepo.findTopByOrderByIdDesc()
                .map(c -> c.getId() + 1)
                .orElse(1);
        control.setId(newId);
        return controlRepo.save(control);
    }

    public boolean deleteControl(int id) {
        Control control = controlRepo.findById(id);
        if (control == null) return false;

        // Delete related SoA entries
        List<Soa> relatedSoa = soaRepo.findByControlId(id);
        soaRepo.deleteAll(relatedSoa);

        controlRepo.delete(control);
        return true;
    }
}
