package com.example.demo.controllers;

import com.example.demo.models.FarmWorker;
import com.example.demo.repositories.FarmWorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmworker")
public class FarmWorkerController {

    @Autowired
    private FarmWorkerRepository farmWorkerRepository;

    @GetMapping
    public List<FarmWorker> getAllFarmWorkers() {
        return farmWorkerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmWorker> getFarmWorkerById(@PathVariable Long id) {
        FarmWorker worker = farmWorkerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmWorker with ID " + id + " not found"));
        return ResponseEntity.ok(worker);
    }

    @PostMapping
    public FarmWorker createFarmWorker(@RequestBody FarmWorker worker) {
        return farmWorkerRepository.save(worker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmWorker> updateFarmWorker(@PathVariable Long id, @RequestBody FarmWorker updatedWorker) {
        FarmWorker worker = farmWorkerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmWorker with ID " + id + " not found"));
        worker.setName(updatedWorker.getName());
        worker.setJobType(updatedWorker.getJobType());

        return ResponseEntity.ok(farmWorkerRepository.save(worker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmWorker(@PathVariable Long id) {
        FarmWorker worker = farmWorkerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmWorker with ID " + id + " not found"));
        farmWorkerRepository.delete(worker);
        return ResponseEntity.noContent().build();
    }
}
