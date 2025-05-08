package com.example.demo.controllers;

import jakarta.persistence.EntityNotFoundException;
import com.example.demo.models.Farm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositories.FarmRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FarmController {

    @Autowired
    private FarmRepository farmRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm with ID " + id + "not Found"));

        return ResponseEntity.ok(farm);
    }

    @PostMapping("/{id}")
    public Farm createFarm(@RequestBody Farm farm){
        return farmRepository.save(farm);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable Long id, @RequestBody Farm updatedFarm){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm with ID " + id + " not found"));
        farm.setName(updatedFarm.getName());
        farm.setLocation(updatedFarm.getLocation());
        farm.setOwner(updatedFarm.getOwner());

        return ResponseEntity.ok(farmRepository.save(farm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm with ID " + id + " not found"));
        farmRepository.delete(farm);

        return ResponseEntity.noContent().build();
    }
}
