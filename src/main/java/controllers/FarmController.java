package controllers;

import jakarta.persistence.EntityNotFoundException;
import models.Farm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import repositories.FarmRepository;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    private FarmRepository farmRepository;

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm with ID " + id + "not Found"));

        return ResponseEntity.ok(farm);
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm){
        return farmRepository.save(farm);

    }
}
