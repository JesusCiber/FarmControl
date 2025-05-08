package com.example.demo.controllers;

import com.example.demo.models.FarmJob;
import com.example.demo.repositories.FarmJobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmjob")
public class FarmJobController {

    @Autowired
    private FarmJobRepository farmJobRepository;

    @GetMapping
    public List<FarmJob> getAllFarmJobs() {
        return farmJobRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmJob> getFarmJobById(@PathVariable Long id) {
        FarmJob job = farmJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmJob with ID " + id + " not found"));
        return ResponseEntity.ok(job);
    }

    @PostMapping
    public FarmJob createFarmJob(@RequestBody FarmJob job) {
        return farmJobRepository.save(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmJob> updateFarmJob(@PathVariable Long id, @RequestBody FarmJob updatedJob) {
        FarmJob job = farmJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmJob with ID " + id + " not found"));
        job.setDescription(updatedJob.getDescription());


        return ResponseEntity.ok(farmJobRepository.save(job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmJob(@PathVariable Long id) {
        FarmJob job = farmJobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmJob with ID " + id + " not found"));
        farmJobRepository.delete(job);
        return ResponseEntity.noContent().build();
    }
}
