package com.example.demo.services;

import com.example.demo.models.Farm;

import java.util.List;

public interface FarmService {
    List<Farm> getAllFarms();
    Farm getFarmById(Long id);
    Farm createFarm(Farm farm);
    Farm updateFarm(Long id, Farm updatedFarm);
    void deleteFarm(Long id);
    List<Farm> getFarmsByOwnerId(Long ownerId);
    List<Farm> getFarmsByLocationContaining(String location);
}
