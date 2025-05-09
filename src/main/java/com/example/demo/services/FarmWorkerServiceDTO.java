package com.example.demo.services;

import com.example.demo.DTO.FarmWorkerDTO;

import java.util.List;

public interface FarmWorkerServiceDTO {
    List<FarmWorkerDTO> getAllFarmWorkers();
    FarmWorkerDTO getFarmWorkerById(Long id);
    FarmWorkerDTO createFarmWorker(FarmWorkerDTO farmWorkerDTO);
    FarmWorkerDTO updateFarmWorker(Long id, FarmWorkerDTO updatedFarmWorkerDTO);
    void deleteFarmWorker(Long id);
    List<FarmWorkerDTO> getWorkersByFarmId(Long farmId);
}