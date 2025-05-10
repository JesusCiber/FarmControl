package com.example.demo.services;

import com.example.demo.DTO.FarmDTO;


import java.util.List;

public interface FarmServiceDTO {
    List<FarmDTO> getAllFarms();
    FarmDTO getFarmById(Long id);
    FarmDTO createFarm(FarmDTO farm);
    FarmDTO updateFarm(Long id, FarmDTO updatedFarm);
    void deleteFarm(Long id);
    List<FarmDTO> getFarmsByOwnerId(Long ownerId);
    List<FarmDTO> getFarmsByLocationContaining(String location);
}
