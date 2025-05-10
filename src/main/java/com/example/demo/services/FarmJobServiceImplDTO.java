package com.example.demo.services;


import com.example.demo.DTO.FarmJobDTO;
import com.example.demo.repositories.FarmJobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmJobServiceImplDTO implements FarmJobServiceDTO {

    @Autowired
    private FarmJobRepository farmJobRepository;

    @Override
    public List<FarmJobDTO> getAllFarmJobs() {
        return farmJobRepository.findAll().stream()
                .map(farmJob -> new FarmJobDTO(farmJob))
                .collect(Collectors.toList());
    }

    @Override
    public FarmJobDTO getFarmJobById(Long id) {
        return null;
    }

    @Override
    public FarmJobDTO createFarmJob(FarmJobDTO farmJobDTO) {
        return null;
    }

    @Override
    public FarmJobDTO updateFarmJob(Long id, FarmJobDTO updatedFarmJobDTO) {
        return null;
    }

    @Override
    public void deleteFarmJob(Long id) {

    }

    @Override
    public List<FarmJobDTO> getJobsByFarmId(Long farmId) {
        return List.of();
    }

    @Override
    public List<FarmJobDTO> getJobsByFarmIdAndLocation(Long farmId, String location) {
        return List.of();
    }

}
