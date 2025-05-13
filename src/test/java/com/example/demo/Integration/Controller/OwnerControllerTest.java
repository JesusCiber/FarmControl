package com.example.demo.Integration.Controller;

import com.example.demo.DTO.OwnerDTO;
import com.example.demo.models.Farm;
import com.example.demo.models.Owner;
import com.example.demo.repositories.FarmRepository;
import com.example.demo.repositories.OwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OwnerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private FarmRepository farmRepository;

    private Owner testOwner;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        testOwner = new Owner(null, "Test Owner", "123456789", "testowner@example.com", "password", "Individual", new ArrayList<>());
        testOwner = ownerRepository.save(testOwner);
    }

    @AfterEach
    public void cleanUp() {
        // Eliminar farms asociadas antes de eliminar el owner
        List<Farm> farms = farmRepository.findByOwnerId(testOwner.getId());
        for (Farm farm : farms) {
            farmRepository.deleteById(farm.getId());
        }

        // Eliminar el owner
        if (ownerRepository.existsById(testOwner.getId())) {
            ownerRepository.deleteById(testOwner.getId());
        }
    }

    @Test
    void OwnerCreatedTest() throws Exception {
        OwnerDTO ownerDTO = new OwnerDTO(null, "TestOwner", "987654321", "testowner@gmail.com", new ArrayList<>());
        String requestBody = objectMapper.writeValueAsString(ownerDTO);

        MvcResult result = mockMvc.perform(post("/api/owner-dto")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("TestOwner"));
    }


    @Test
    void getAllOwnersTest() throws Exception {
        Owner owner1 = new Owner(null, "Alice", "555555555", "alice@example.com", "password123", "Individual", new ArrayList<>());
        Owner owner2 = new Owner(null, "Bob", "444444444", "bob@example.com", "password123", "Company", new ArrayList<>());
        ownerRepository.saveAll(List.of(owner1, owner2));

        MvcResult result = mockMvc.perform(get("/api/owner-dto"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Alice"));
        assertTrue(result.getResponse().getContentAsString().contains("Bob"));
    }


    @Test
    void updateOwnerTest() throws Exception {
        OwnerDTO updatedOwnerDTO = new OwnerDTO(testOwner.getId(), "Updated Owner", "999999999", "updated@example.com", new ArrayList<>());
        String requestBody = objectMapper.writeValueAsString(updatedOwnerDTO);

        mockMvc.perform(put("/api/owner-dto/" + testOwner.getId())
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(get("/api/owner-dto/" + testOwner.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Updated Owner"));
    }

    @Test
    void deleteOwnerTest() throws Exception {
        mockMvc.perform(delete("/api/owner-dto/" + testOwner.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        MvcResult result = mockMvc.perform(get("/api/owner-dto"))
                .andReturn();

        assertFalse(result.getResponse().getContentAsString().contains("Test Owner"));
    }
}
