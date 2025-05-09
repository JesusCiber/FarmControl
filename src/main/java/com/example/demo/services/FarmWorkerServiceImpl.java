package com.example.demo.services;

import com.example.demo.DTO.FarmWorkerDTO;
import com.example.demo.models.Farm;
import com.example.demo.models.FarmWorker;
import com.example.demo.repositories.FarmRepository;
import com.example.demo.repositories.FarmWorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Formattable;
import java.util.List;

@Service
public class FarmWorkerServiceImpl implements FarmWorkerService{

    @Autowired
    private FarmWorkerRepository farmWorkerRepository;

    @Override
    public List<FarmWorker> getAllFarmWorkers(){
        return farmWorkerRepository.findAll();
    }

    @Override
    public FarmWorker getFarmWorkerById(Long id){
        return farmWorkerRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(("FarmWorker with ID " + id + " not found")));
    }

    @Override
    public FarmWorker createFarmWorker(FarmWorker farmWorker){
        return farmWorkerRepository.save(farmWorker);
    }

    @Override
    public FarmWorker updateFarmWorker(Long id, FarmWorker updateFarmWorker){
        FarmWorker farmWorker = getFarmWorkerById(id);
        farmWorker.setName(updateFarmWorker.getName());
        farmWorker.setJobType(updateFarmWorker.getJobType());
        return farmWorkerRepository.save(farmWorker);
    }

    @Override
    public void deleteFarmWorker(Long id) {
        FarmWorker farmWorker = getFarmWorkerById(id);
        farmWorkerRepository.delete(farmWorker);
    }

    @Override
    public List<FarmWorker> getWorkersByFarmId(Long farmId) {
        List<FarmWorker> workers = farmWorkerRepository.findByFarmId(farmId);
        if (workers.isEmpty()) {
            throw new EntityNotFoundException("No workers found for farm ID: " + farmId);
        }
        return workers;
    }

}
