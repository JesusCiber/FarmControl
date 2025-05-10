package com.example.demo.services;

import com.example.demo.DTO.FarmDTO;
import com.example.demo.models.Farm;
import com.example.demo.repositories.FarmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmServiceImplDTO implements FarmServiceDTO {

    @Autowired
    private FarmRepository farmRepository;

    @Override
    public List<FarmDTO> getAllFarms() {
        return farmRepository.findAll().stream()
                .map(farm -> new FarmDTO(farm))
                .collect(Collectors.toList());
    }

    @Override
    public FarmDTO getFarmById(Long id) {
        return null;
    }

    @Override
    public FarmDTO createFarm(FarmDTO farm) {
        return null;
    }

    @Override
    public FarmDTO updateFarm(Long id, FarmDTO updatedFarm) {
        return null;
    }

    @Override
    public void deleteFarm(Long id) {

    }

    @Override
    public List<FarmDTO> getFarmsByOwnerId(Long ownerId) {
        return List.of();
    }

    @Override
    public List<FarmDTO> getFarmsByLocationContaining(String location) {
        return List.of();
    }

}
