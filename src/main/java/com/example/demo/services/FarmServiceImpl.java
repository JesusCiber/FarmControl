package com.example.demo.services;

import com.example.demo.models.Farm;
import com.example.demo.repositories.FarmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(("Farm with ID " + id + " not found")));
    }

    @Override
    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }


    @Override
    public Farm updateFarm(Long id, Farm updatedFarm) {
        Farm farm = getFarmById(id);
        farm.setName(updatedFarm.getName());
        farm.setLocation(updatedFarm.getLocation());
        farm.setOwner(updatedFarm.getOwner());
        return farmRepository.save(farm);
    }

    @Override
    public void deleteFarm(Long id) {
        Farm farm = getFarmById(id);
        farmRepository.delete(farm);
    }

    @Override
    public List<Farm> getFarmsByOwnerId(Long ownerId) {
        List<Farm> farms = farmRepository.findByOwnerId(ownerId);
        if (farms.isEmpty()) {
            throw new EntityNotFoundException("No farms found for owner ID: " + ownerId);
        }
        return farms;
    }

    @Override
    public List<Farm> getFarmsByLocationContaining(String location) {
        List<Farm> farms = farmRepository.findByLocationContaining(location);
        if (farms.isEmpty()) {
            throw new EntityNotFoundException("No farms found containing location: " + location);
        }
        return farms;
    }
}
