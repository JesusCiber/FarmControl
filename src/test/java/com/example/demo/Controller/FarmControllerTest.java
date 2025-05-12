package com.example.demo.Controller;

import com.example.demo.DTO.FarmDTO;
import com.example.demo.models.Farm;
import com.example.demo.models.Owner;
import com.example.demo.repositories.FarmRepository;
import com.example.demo.repositories.OwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FarmControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    private Owner testOwner;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        farmRepository.deleteAll();
        ownerRepository.deleteAll();

        // Crear y guardar un único Owner de prueba para todos los tests
        testOwner = new Owner(null, "Test Owner", "123456789", "testowner@example.com", "password", "Individual", new ArrayList<>());
        testOwner = ownerRepository.save(testOwner);
    }

    @AfterEach
    public void tearDown() {
        farmRepository.deleteAll();
        ownerRepository.deleteAll();
    }

    @Test
    void CreatedFarm() throws Exception {
        FarmDTO farmDTO = new FarmDTO(null, "Olivos Farm", "Jaen", testOwner.getId());
        String requestBody = objectMapper.writeValueAsString(farmDTO);

        MvcResult result = mockMvc.perform(post("/api/farm-dto")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Olivos Farm"));
    }

    @Test
    void getAllFarms() throws Exception {
        Farm farm1 = new Farm(null, "Sunny Farm", "Madrid", testOwner, new ArrayList<>(), new ArrayList<>());
        Farm farm2 = new Farm(null, "Green Valley", "Barcelona", testOwner, new ArrayList<>(), new ArrayList<>());
        farmRepository.saveAll(List.of(farm1, farm2));

        MvcResult result = mockMvc.perform(get("/api/farm-dto"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Sunny Farm"));
        assertTrue(result.getResponse().getContentAsString().contains("Green Valley"));
    }

    @Test
    void updateFarm() throws Exception {
        Farm farm = new Farm(null, "Old Farm Name", "Old Location", testOwner, new ArrayList<>(), new ArrayList<>());
        farm = farmRepository.save(farm);

        FarmDTO updatedFarmDTO = new FarmDTO(farm.getId(), "Updated Farm", "Updated Location", testOwner.getId());
        String requestBody = objectMapper.writeValueAsString(updatedFarmDTO);
        //Solicitud PUT
        mockMvc.perform(put("/api/farm-dto/" + farm.getId())
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verificamos que esta actualizada correctamente
        MvcResult result = mockMvc.perform(get("/api/farm-dto/" + farm.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Updated Farm"));
        assertTrue(result.getResponse().getContentAsString().contains("Updated Location"));
    }

}