package com.example.demo.controllers;

import com.example.demo.services.FarmService;
import jakarta.persistence.EntityNotFoundException;
import com.example.demo.models.Farm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositories.FarmRepository;

import java.util.List;

@RestController
@RequestMapping("/api/farm")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id){
      return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Farm>> getFarmsByOwnerId(@PathVariable Long ownerId) {
        return ResponseEntity.ok(farmService.getFarmsByOwnerId(ownerId));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Farm>> getFarmsByLocationContaining(@PathVariable String location) {
        return ResponseEntity.ok(farmService.getFarmsByLocationContaining(location));
    }


    @PostMapping("/{id}")
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm){
        return new ResponseEntity<>(farmService.createFarm(farm), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable Long id, @RequestBody Farm updatedFarm){
        return ResponseEntity.ok(farmService.updateFarm(id, updatedFarm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id){
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }


}
