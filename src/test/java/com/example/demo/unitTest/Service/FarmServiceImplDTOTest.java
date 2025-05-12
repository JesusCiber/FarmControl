package com.example.demo.unitTest.Service;

import com.example.demo.DTO.FarmDTO;
import com.example.demo.models.Farm;
import com.example.demo.models.Owner;
import com.example.demo.repositories.FarmRepository;
import com.example.demo.repositories.OwnerRepository;
import com.example.demo.services.FarmServiceImplDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FarmServiceImplDTOTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private FarmServiceImplDTO farmService;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFarms() {
        Owner owner = new Owner(1L, "Ana", "123456789", "ana@mail.com", "pass", "Individual", null);
        Farm farm1 = new Farm(1L, "Finca A", "Madrid", owner, null, null);
        Farm farm2 = new Farm(2L, "Finca B", "Toledo", owner, null, null);

        when(farmRepository.findAll()).thenReturn(Arrays.asList(farm1, farm2));

        List<FarmDTO> result = farmService.getAllFarms();

        assertEquals(2, result.size());
        assertEquals("Finca A", result.get(0).getName());
        verify(farmRepository, times(1)).findAll();
    }

    @Test
    void testGetFarmById_Found() {
        Owner owner = new Owner();
        Farm farm = new Farm(1L, "Finca A", "Madrid", owner, null, null);
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        FarmDTO result = farmService.getFarmById(1L);

        assertNotNull(result);
        assertEquals("Finca A", result.getName());
    }

    @Test
    void testGetFarmById_NotFound() {
        when(farmRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> farmService.getFarmById(99L));
    }

    @Test
    void testCreateFarm() {
        Owner owner = new Owner(1L, "Ana", "123456789", "ana@mail.com", "pass", "Individual", null);
        FarmDTO inputDTO = new FarmDTO(null, "Nueva Finca", "Valencia", 1L);
        Farm savedFarm = new Farm(1L, "Nueva Finca", "Valencia", owner, null, null);

        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(farmRepository.save(any(Farm.class))).thenReturn(savedFarm);

        FarmDTO result = farmService.createFarm(inputDTO);

        assertNotNull(result);
        assertEquals("Nueva Finca", result.getName());
        verify(ownerRepository).findById(1L);
        verify(farmRepository).save(any(Farm.class));
    }

    @Test
    void testDeleteFarm() {
        Owner owner = new Owner();
        Farm farm = new Farm(1L, "Finca X", "Granada", owner, null, null);

        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        farmService.deleteFarm(1L);

        verify(farmRepository).delete(farm);
    }
}
