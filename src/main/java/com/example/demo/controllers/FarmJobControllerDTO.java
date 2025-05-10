package com.example.demo.controllers;


import com.example.demo.DTO.FarmJobDTO;
import com.example.demo.services.FarmJobServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
