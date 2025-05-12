package com.example.demo.controllers;


import com.example.demo.DTO.FarmDTO;
import com.example.demo.DTO.OwnerDTO;
import com.example.demo.services.OwnerServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner-dto")
public class OwnerControllerDTO {

    @Autowired
    private OwnerServiceDTO ownerServiceDTO;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OwnerDTO>> getAllOwner() {
        return ResponseEntity.ok(ownerServiceDTO.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerServiceDTO.getOwnerById(id));
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<OwnerDTO>> getOwnersByFarmId(@PathVariable Long farmId) {
        return ResponseEntity.ok(ownerServiceDTO.getOwnersByFarmId(farmId));
    }

    @GetMapping("/farm/{farmId}/location/{location}")
    public ResponseEntity<List<OwnerDTO>> getOwnersByFarmIdAndLocation(@PathVariable Long farmId, @PathVariable String location) {
        return ResponseEntity.ok(ownerServiceDTO.getOwnersByFarmIdAndLocation(farmId, location));
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO ownerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerServiceDTO.createOwner(ownerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, @RequestBody OwnerDTO updatedOwnerDTO) {
        return ResponseEntity.ok(ownerServiceDTO.updateOwner(id, updatedOwnerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerServiceDTO.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }



}
