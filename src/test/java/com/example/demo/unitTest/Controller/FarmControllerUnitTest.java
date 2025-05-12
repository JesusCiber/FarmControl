package com.example.demo.unitTest.Controller;

import com.example.demo.DTO.FarmDTO;
import com.example.demo.controllers.FarmControllerDTO;
import com.example.demo.services.FarmServiceDTO;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FarmControllerUnitTest {

    @Mock
    private FarmServiceDTO farmService;

    @InjectMocks
    private FarmControllerDTO farmController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(farmController).build();
    }

    @Test
    public void createdFarm() throws Exception {
        FarmDTO farmDTO = new FarmDTO(null, "Olivos Farm", "Jaen", 1L);
        String requestBody = objectMapper.writeValueAsString(farmDTO);

        when(farmService.createFarm(any(FarmDTO.class)))
                .thenReturn(new FarmDTO(1L, "Olivos Farm", "Jaen", 1L));

        MvcResult result = mockMvc.perform(post("/api/farm-dto")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Olivos Farm"));
    }
}
