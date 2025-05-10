package com.example.demo.services;


import com.example.demo.DTO.OwnerDTO;

import java.util.List;

public interface OwnerServiceDTO {
    List<OwnerDTO> getAllOwners();
    OwnerDTO getOwnerById(Long id);
    OwnerDTO createOwner(OwnerDTO ownerDTO);
    OwnerDTO updateOwner(Long id, OwnerDTO updatedOwnerDTO);
    void deleteOwner(Long id);
    List<OwnerDTO> getOwnersByFarmId(Long farmId);
    List<OwnerDTO> getOwnersByFarmIdAndLocation(Long farmId, String location);
}
