package com.example.demo.controllers;


import com.example.demo.DTO.FarmJobDTO;
import com.example.demo.services.FarmJobServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmjob-dto")
public class FarmJobControllerDTO {

    @Autowired
    private FarmJobServiceDTO farmJobServiceDTO;

    @GetMapping
    public ResponseEntity<List<FarmJobDTO>> getAllFarmJobs() {
        return ResponseEntity.ok(farmJobServiceDTO.getAllFarmJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmJobDTO> getFarmJobById(@PathVariable Long id) {
        return ResponseEntity.ok(farmJobServiceDTO.getFarmJobById(id));
    }

    @PostMapping
    public ResponseEntity<FarmJobDTO> createFarmJob(@RequestBody FarmJobDTO farmJobDTO) {
        return new ResponseEntity<>(farmJobServiceDTO.createFarmJob(farmJobDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmJobDTO> updateFarmJob(@PathVariable Long id, @RequestBody FarmJobDTO updatedFarmJob) {
        return ResponseEntity.ok(farmJobServiceDTO.updateFarmJob(id, updatedFarmJob));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmJob(@PathVariable Long id) {
        farmJobServiceDTO.deleteFarmJob(id);
        return ResponseEntity.noContent().build();
    }
}
