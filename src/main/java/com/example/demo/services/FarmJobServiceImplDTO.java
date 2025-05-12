package com.example.demo.services;


import com.example.demo.DTO.FarmJobDTO;
import com.example.demo.models.Farm;
import com.example.demo.models.FarmJob;
import com.example.demo.repositories.FarmJobRepository;

import com.example.demo.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmJobServiceImplDTO implements FarmJobServiceDTO {

    @Autowired
    private FarmJobRepository farmJobRepository;
    @Autowired
    private FarmRepository farmRepository;

    @Override
    public List<FarmJobDTO> getAllFarmJobs() {
        return farmJobRepository.findAll().stream()
                .map(farmJob -> new FarmJobDTO(farmJob))
                .collect(Collectors.toList());
    }

    @Override
    public FarmJobDTO getFarmJobById(Long id) {
        return farmJobRepository.findById(id)
                .map(FarmJobDTO::new)
                .orElseThrow(() -> new RuntimeException("Farm Job not found with ID: " + id));
    }

    @Override
    public FarmJobDTO createFarmJob(FarmJobDTO farmJobDTO) {
        // Buscar la granja antes de asignarla al trabajo
        Farm farm = farmRepository.findById(farmJobDTO.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found with ID: " + farmJobDTO.getFarmId()));

        FarmJob farmJob = new FarmJob(null, farmJobDTO.getDescription(), farm);
        FarmJob savedFarmJob = farmJobRepository.save(farmJob);
        return new FarmJobDTO(savedFarmJob);
    }

    @Override
    public FarmJobDTO updateFarmJob(Long id, FarmJobDTO updatedFarmJobDTO) {
        FarmJob farmJob = farmJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm Job not found with ID: " + id));

        farmJob.setDescription(updatedFarmJobDTO.getDescription());

        FarmJob updatedFarmJob = farmJobRepository.save(farmJob);
        return new FarmJobDTO(updatedFarmJob);
    }

    @Override
    public void deleteFarmJob(Long id) {
        FarmJob farmJob = farmJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm Job not found with ID: " + id));

        farmJobRepository.delete(farmJob);
    }

}
