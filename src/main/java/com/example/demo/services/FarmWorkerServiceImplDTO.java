package com.example.demo.services;


import com.example.demo.DTO.FarmWorkerDTO;
import com.example.demo.models.FarmWorker;
import com.example.demo.repositories.FarmWorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmWorkerServiceImplDTO implements FarmWorkerServiceDTO {

    @Autowired
    private FarmWorkerRepository farmWorkerRepository;

   @Override
    public List<FarmWorkerDTO> getAllFarmWorkers() {
        return farmWorkerRepository.findAll().stream()
                .map(worker -> new FarmWorkerDTO(worker))
                .collect(Collectors.toList());
    }

    @Override
    public FarmWorkerDTO getFarmWorkerById(Long id) {
        FarmWorker worker = farmWorkerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmWorker con ID " + id + " no encontrado"));

        return new FarmWorkerDTO(worker);
    }

    @Override
    public FarmWorkerDTO createFarmWorker(FarmWorkerDTO farmWorkerDTO) {
        if (farmWorkerDTO.getName() == null || farmWorkerDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (farmWorkerDTO.getPhoneNumber() == null || farmWorkerDTO.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        if (farmWorkerDTO.getEmail() == null || farmWorkerDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
       FarmWorker worker = new FarmWorker();
        worker.setName(farmWorkerDTO.getName());
        worker.setPhoneNumber(farmWorkerDTO.getPhoneNumber());
        worker.setEmail(farmWorkerDTO.getEmail());
        worker.setJobType(farmWorkerDTO.getJobType());

        return new FarmWorkerDTO(farmWorkerRepository.save(worker));
    }

    @Override
    public FarmWorkerDTO updateFarmWorker(Long id, FarmWorkerDTO updatedFarmWorkerDTO) {
        FarmWorker worker = farmWorkerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmWorker con ID " + id + " no encontrado"));

        worker.setName(updatedFarmWorkerDTO.getName());
        worker.setPhoneNumber(updatedFarmWorkerDTO.getPhoneNumber());
        worker.setEmail(updatedFarmWorkerDTO.getEmail());
        worker.setJobType(updatedFarmWorkerDTO.getJobType());

        return new FarmWorkerDTO(farmWorkerRepository.save(worker));
    }

    @Override
    public void deleteFarmWorker(Long id) {
        FarmWorker worker = farmWorkerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FarmWorker con ID " + id + " no encontrado"));
        farmWorkerRepository.delete(worker);
    }

    @Override
    public List<FarmWorkerDTO> getWorkersByFarmId(Long farmId) {
        List<FarmWorker> workers = farmWorkerRepository.findByFarmId(farmId);
        if (workers.isEmpty()) {
            throw new EntityNotFoundException("No workers found for farm ID: " + farmId);
        }
        return workers.stream()
                .map(worker -> new FarmWorkerDTO(worker))
                .collect(Collectors.toList());
    }


}
