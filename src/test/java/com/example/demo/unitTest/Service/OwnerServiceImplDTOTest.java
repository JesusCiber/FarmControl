package com.example.demo.unitTest.Service;

import com.example.demo.DTO.OwnerDTO;
import com.example.demo.models.Owner;
import com.example.demo.repositories.OwnerRepository;
import com.example.demo.services.OwnerServiceImplDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OwnerServiceImplDTOTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImplDTO ownerService;

    private Owner testOwner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testOwner = new Owner(1L, "Test Owner", "123456789", "testowner@example.com", "password", "Individual", new ArrayList<>());
    }

    @Test
    void createOwnerTest() {
        OwnerDTO ownerDTO = new OwnerDTO(null, "New Owner", "987654321", "newowner@example.com",null);

        // Simulamos que el repositorio va a devolver el Owner creado
        when(ownerRepository.save(any(Owner.class))).thenReturn(testOwner);

        // Llamamos al método del servicio
        OwnerDTO result = ownerService.createOwner(ownerDTO);

        // Verificamos que el nombre del Owner creado sea el esperado
        assertEquals("Test Owner", result.getName());
        verify(ownerRepository, times(1)).save(any(Owner.class)); // Verifica que se llame a save() una vez
    }
}
