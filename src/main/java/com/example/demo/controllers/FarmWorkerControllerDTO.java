package com.example.demo.controllers;

import com.example.demo.DTO.FarmWorkerDTO;
import com.example.demo.services.FarmWorkerServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmworker-dto")
public class FarmWorkerControllerDTO {

    @Autowired
    private FarmWorkerServiceDTO farmWorkerServiceDTO;

    @GetMapping
    public ResponseEntity<List<FarmWorkerDTO>> getAllFarmWorkers() {
        return ResponseEntity.ok(farmWorkerServiceDTO.getAllFarmWorkers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmWorkerDTO> getFarmWorkerById(@PathVariable Long id) {
        return ResponseEntity.ok(farmWorkerServiceDTO.getFarmWorkerById(id));
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<FarmWorkerDTO>> getWorkersByFarmId(@PathVariable Long farmId) {
        return ResponseEntity.ok(farmWorkerServiceDTO.getWorkersByFarmId(farmId));
    }

    @PostMapping
    public ResponseEntity<FarmWorkerDTO> createFarmWorker(@RequestBody FarmWorkerDTO workerDTO) {
        return ResponseEntity.ok(farmWorkerServiceDTO.createFarmWorker(workerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmWorkerDTO> updateFarmWorker(@PathVariable Long id, @RequestBody FarmWorkerDTO updatedWorkerDTO) {
        return ResponseEntity.ok(farmWorkerServiceDTO.updateFarmWorker(id, updatedWorkerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmWorker(@PathVariable Long id) {
        farmWorkerServiceDTO.deleteFarmWorker(id);
        return ResponseEntity.noContent().build();
    }
}
