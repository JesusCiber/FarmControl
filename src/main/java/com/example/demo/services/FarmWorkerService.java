package com.example.demo.services;

import com.example.demo.models.FarmWorker;

import java.util.List;

public interface FarmWorkerService {
    List<FarmWorker> getAllFarmWorkers();
    FarmWorker getFarmWorkerById(Long id);
    FarmWorker createFarmWorker(FarmWorker farmWorker);
    FarmWorker updateFarmWorker(Long id, FarmWorker updatedFarmWorker);
    void deleteFarmWorker(Long id);
    List<FarmWorker> getWorkersByFarmId(Long farmId);

}
