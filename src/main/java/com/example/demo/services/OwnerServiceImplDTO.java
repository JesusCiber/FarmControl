package com.example.demo.services;


import com.example.demo.DTO.OwnerDTO;
import com.example.demo.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImplDTO implements OwnerServiceDTO{

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(owner -> new OwnerDTO(owner))
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDTO getOwnerById(Long id) {
        return null;
    }

    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        return null;
    }

    @Override
    public OwnerDTO updateOwner(Long id, OwnerDTO updatedOwnerDTO) {
        return null;
    }


    @Override
    public void deleteOwner(Long id) {

    }

    @Override
    public List<OwnerDTO> getOwnersByFarmId(Long farmId) {
        return List.of();
    }

    @Override
    public List<OwnerDTO> getOwnersByFarmIdAndLocation(Long farmId, String location) {
        return List.of();
    }


}
