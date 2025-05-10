package com.example.demo.services;

import com.example.demo.DTO.FarmJobDTO;

import java.util.List;

public interface FarmJobServiceDTO {
    List<FarmJobDTO> getAllFarmJobs();
    FarmJobDTO getFarmJobById(Long id);
    FarmJobDTO createFarmJob(FarmJobDTO farmJobDTO);
    FarmJobDTO updateFarmJob(Long id, FarmJobDTO updatedFarmJobDTO);
    void deleteFarmJob(Long id);
    List<FarmJobDTO> getJobsByFarmId(Long farmId);
    List<FarmJobDTO> getJobsByFarmIdAndLocation(Long farmId, String location);
}
