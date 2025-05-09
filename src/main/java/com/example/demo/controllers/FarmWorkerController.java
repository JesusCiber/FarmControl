package com.example.demo.controllers;

import com.example.demo.models.FarmWorker;
import com.example.demo.services.FarmWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmworker")
public class FarmWorkerController {

    @Autowired
    private FarmWorkerService farmWorkerService;

    @GetMapping
    public ResponseEntity<List<FarmWorker>> getAllFarmWorkers() {
        return ResponseEntity.ok(farmWorkerService.getAllFarmWorkers());
    }

    // GET workers by farm ID
    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<FarmWorker>> getWorkersByFarmId(@PathVariable Long farmId) {
        return ResponseEntity.ok(farmWorkerService.getWorkersByFarmId(farmId));
    }



    // GET worker by ID
    @GetMapping("/{id}")
    public ResponseEntity<FarmWorker> getFarmWorkerById(@PathVariable Long id) {
        return ResponseEntity.ok(farmWorkerService.getFarmWorkerById(id));
    }


    // POST create worker
    @PostMapping
    public ResponseEntity<FarmWorker> createFarmWorker(@RequestBody FarmWorker worker) {
        return ResponseEntity.ok(farmWorkerService.createFarmWorker(worker));
    }


    // PUT update worker
    @PutMapping("/{id}")
    public ResponseEntity<FarmWorker> updateFarmWorker(@PathVariable Long id, @RequestBody FarmWorker updatedWorker) {
        return ResponseEntity.ok(farmWorkerService.updateFarmWorker(id, updatedWorker));
    }


    // DELETE worker
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmWorker(@PathVariable Long id) {
        farmWorkerService.deleteFarmWorker(id);
        return ResponseEntity.noContent().build();
    }

}
