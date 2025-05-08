package com.example.demo.controllers;


import com.example.demo.models.Owner;
import com.example.demo.repositories.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner with ID " + id + " not found"));
        return ResponseEntity.ok(owner);
    }

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerRepository.save(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner with ID " + id + " not found"));
        owner.setType(updatedOwner.getType());

        return ResponseEntity.ok(ownerRepository.save(owner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner with ID " + id + " not found"));
        ownerRepository.delete(owner);
        return ResponseEntity.noContent().build();
    }
}
