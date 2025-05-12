package com.example.demo.controllers;

import com.example.demo.DTO.FarmDTO;
import com.example.demo.services.FarmServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/farm-dto")
public class FarmControllerDTO {
    @Autowired
    private FarmServiceDTO farmServiceDTO;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FarmDTO>> getAllFarms() {
        return ResponseEntity.ok(farmServiceDTO.getAllFarms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id){
        return ResponseEntity.ok(farmServiceDTO.getFarmById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<FarmDTO>> getFarmsByOwnerId(@PathVariable Long ownerId) {
        return ResponseEntity.ok(farmServiceDTO.getFarmsByOwnerId(ownerId));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<FarmDTO>> getFarmsByLocationContaining(@PathVariable String location) {
        return ResponseEntity.ok(farmServiceDTO.getFarmsByLocationContaining(location));
    }


    @PostMapping()
    public ResponseEntity<FarmDTO> createFarm(@RequestBody FarmDTO farmDTO){
        return new ResponseEntity<>(farmServiceDTO.createFarm(farmDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Long id, @RequestBody FarmDTO updatedFarm){
        return ResponseEntity.ok(farmServiceDTO.updateFarm(id, updatedFarm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id){
        farmServiceDTO.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }

}
