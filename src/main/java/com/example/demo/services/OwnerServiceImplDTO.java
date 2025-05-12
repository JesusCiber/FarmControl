package com.example.demo.services;


import com.example.demo.DTO.OwnerDTO;
import com.example.demo.models.Owner;
import com.example.demo.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + id));
        return new OwnerDTO(owner);
    }

    @Override
    public List<OwnerDTO> getOwnersByFarmId(Long farmId) {
        return ownerRepository.findAll().stream() // 🔹 Buscar TODOS los Owners
                .map(OwnerDTO::new) // 🔹 Convertir cada Owner a DTO
                .filter(ownerDTO -> ownerDTO.getFarms().stream()
                        .anyMatch(farmDTO -> farmDTO.getId().equals(farmId))) // 🔹 Filtrar por farmId dentro de OwnerDTO
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner(null, ownerDTO.getName(), ownerDTO.getPhoneNumber(), ownerDTO.getEmail(), "password", "Individual", new ArrayList<>());
        Owner savedOwner = ownerRepository.save(owner);
        return new OwnerDTO(savedOwner);
    }

    @Override
    public List<OwnerDTO> getOwnersByFarmIdAndLocation(Long farmId, String location) {
        return ownerRepository.findAll().stream() // 🔹 Buscar TODOS los Owners
                .map(OwnerDTO::new) // 🔹 Convertir cada Owner a DTO
                .filter(ownerDTO -> ownerDTO.getFarms().stream()
                        .anyMatch(farmDTO -> farmDTO.getId().equals(farmId) && farmDTO.getLocation().equals(location))) // 🔹 Filtrar por farmId y ubicación
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDTO updateOwner(Long id, OwnerDTO updatedOwnerDTO) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + id));

        owner.setName(updatedOwnerDTO.getName());
        owner.setPhoneNumber(updatedOwnerDTO.getPhoneNumber());
        owner.setEmail(updatedOwnerDTO.getEmail());

        Owner savedOwner = ownerRepository.save(owner);
        return new OwnerDTO(savedOwner);
    }


    @Override
    public void deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + id));
        ownerRepository.delete(owner);
    }

}
