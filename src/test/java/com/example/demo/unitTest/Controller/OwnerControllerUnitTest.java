package com.example.demo.unitTest.Controller;

import com.example.demo.DTO.OwnerDTO;
import com.example.demo.controllers.OwnerControllerDTO;
import com.example.demo.services.OwnerServiceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerUnitTest {

    @Mock
    private OwnerServiceDTO ownerService;

    @InjectMocks
    private OwnerControllerDTO ownerController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void ownerCreatedTest() throws Exception {
        OwnerDTO ownerDTO = new OwnerDTO(null, "John Doe", "987654321", "john@example.com", new ArrayList<>());
        String requestBody = objectMapper.writeValueAsString(ownerDTO);

        when(ownerService.createOwner(any(OwnerDTO.class)))
                .thenReturn(new OwnerDTO(1L, "John Doe", "987654321", "john@example.com", new ArrayList<>()));

        MvcResult result = mockMvc.perform(post("/api/owner-dto")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("John Doe"));
    }

    @Test
    void getAllOwnersTest() throws Exception {
        List<OwnerDTO> mockOwners = List.of(
                new OwnerDTO(1L, "Alice", "555555555", "alice@example.com", new ArrayList<>()),
                new OwnerDTO(2L, "Bob", "444444444", "bob@example.com", new ArrayList<>())
        );

        when(ownerService.getAllOwners()).thenReturn(mockOwners);

        MvcResult result = mockMvc.perform(get("/api/owner-dto"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Alice"));
        assertTrue(result.getResponse().getContentAsString().contains("Bob"));
    }

    @Test
    void updateOwnerTest() throws Exception {
        OwnerDTO updatedOwnerDTO = new OwnerDTO(1L, "Updated Owner", "999999999", "updated@example.com", new ArrayList<>());
        String requestBody = objectMapper.writeValueAsString(updatedOwnerDTO);

        when(ownerService.updateOwner(eq(1L), any(OwnerDTO.class))).thenReturn(updatedOwnerDTO);

        mockMvc.perform(put("/api/owner-dto/1")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Opcional: este GET también debe estar simulado si esperas algo en la respuesta
        when(ownerService.getOwnerById(1L)).thenReturn(updatedOwnerDTO);

        MvcResult result = mockMvc.perform(get("/api/owner-dto/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Updated Owner"));
    }

    @Test
    void deleteOwnerTest() throws Exception {
        doNothing().when(ownerService).deleteOwner(1L);

        mockMvc.perform(delete("/api/owner-dto/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Este GET también debe ser simulado si esperas una lista vacía
        when(ownerService.getAllOwners()).thenReturn(new ArrayList<>());

        MvcResult result = mockMvc.perform(get("/api/owner-dto"))
                .andReturn();

        assertFalse(result.getResponse().getContentAsString().contains("Test Owner"));
    }
}
