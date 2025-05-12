package com.example.demo.services;

import com.example.demo.DTO.FarmDTO;
import com.example.demo.exceptions.GlobalExceptionHandler;
import com.example.demo.models.Farm;
import com.example.demo.models.Owner;
import com.example.demo.repositories.FarmRepository;
import com.example.demo.repositories.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmServiceImplDTO implements FarmServiceDTO {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<FarmDTO> getAllFarms() {
        return farmRepository.findAll().stream()
                .map(farm -> new FarmDTO(farm))
                .collect(Collectors.toList());
    }

    @Override
    public FarmDTO getFarmById(Long id) {
        return farmRepository.findById(id)
                .map(FarmDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found with ID: " + id));
    }

    @Override
    public List<FarmDTO> getFarmsByOwnerId(Long ownerId) {
        return farmRepository.findByOwnerId(ownerId).stream()
                .map(FarmDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<FarmDTO> getFarmsByLocationContaining(String location) {
        List<FarmDTO> farms = farmRepository.findByLocationContaining(location).stream()
                .map(FarmDTO::new)
                .collect(Collectors.toList());
        if (farms.isEmpty()) {
            throw new GlobalExceptionHandler.LocationNotFoundException(location);
        }

        return farms;
    }

    @Override
    public FarmDTO createFarm(FarmDTO farmDTO) {
        // Buscar un Owner válido antes de asignarlo a la Farm
        Owner owner = ownerRepository.findById(farmDTO.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + farmDTO.getOwnerId()));

        Farm farm = new Farm(null, farmDTO.getName(), farmDTO.getLocation(), owner, new ArrayList<>(), new ArrayList<>());
        Farm savedFarm = farmRepository.save(farm);
        return new FarmDTO(savedFarm);
    }

    @Override
    public FarmDTO updateFarm(Long id, FarmDTO updatedFarmDTO) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found with ID: " + id));

        farm.setName(updatedFarmDTO.getName());
        farm.setLocation(updatedFarmDTO.getLocation());

        Farm updatedFarm = farmRepository.save(farm);
        return new FarmDTO(updatedFarm);
    }


    @Override
    public void deleteFarm(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found with ID: " + id));

        Owner owner = farm.getOwner();

        if (owner == null) {
            throw new EntityNotFoundException("Owner for Farm ID " + id + " not found.");
        }

        farmRepository.delete(farm);
    }

}
