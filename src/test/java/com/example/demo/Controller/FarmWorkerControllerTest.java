package com.example.demo.Controller;


import com.example.demo.DTO.FarmDTO;
import com.example.demo.DTO.FarmWorkerDTO;
import com.example.demo.DTO.OwnerDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.controllers.UserControllerDTO;
import com.example.demo.models.Farm;
import com.example.demo.models.FarmWorker;
import com.example.demo.models.Owner;
import com.example.demo.models.User;
import com.example.demo.repositories.FarmRepository;
import com.example.demo.repositories.FarmWorkerRepository;
import com.example.demo.repositories.OwnerRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.FarmServiceDTO;
import com.example.demo.services.FarmWorkerServiceDTO;
import com.example.demo.services.OwnerServiceDTO;
import com.example.demo.services.UserServiceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FarmWorkerControllerTest {

    @Autowired
    private FarmWorkerRepository farmWorkerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    private UserServiceDTO userServiceDTO;
    private UserControllerDTO userControllerDTO;
    private OwnerServiceDTO ownerService;
    private FarmServiceDTO farmServiceDTO;
    private FarmWorkerServiceDTO farmWorkerServiceDTO;


    @Test
    public void testCreateFarmWorker() {
        UserDTO userDTO = new UserDTO(null, "Carlos Rodríguez", "carlos@example.com", "555-1234");
        UserDTO savedUserDTO = userServiceDTO.createUser(userDTO);

        // 2️⃣ Convertir el UserDTO en OwnerDTO y guardarlo como Owner
        OwnerDTO ownerDTO = new OwnerDTO(savedUserDTO.getId(), savedUserDTO.getName(), savedUserDTO.getPhoneNumber(), savedUserDTO.getEmail());
        OwnerDTO savedOwnerDTO = ownerService.createOwner(ownerDTO);

        // 3️⃣ Crear una FarmDTO y asignarla al OwnerDTO
        FarmDTO farmDTO = new FarmDTO(null, "Sunny Fields", "Madrid", savedOwnerDTO);
        FarmDTO savedFarmDTO = farmServiceDTO.createFarm(farmDTO);

        // 4️⃣ Crear un FarmWorkerDTO y asignarle la FarmDTO
        FarmWorkerDTO farmWorkerDTO = new FarmWorkerDTO(null, "Harvest Manager", savedFarmDTO);
        FarmWorkerDTO savedFarmWorkerDTO = farmWorkerServiceDTO.createFarmWorker(farmWorkerDTO);

        // ✅ Verificaciones
        assertNotNull(savedFarmWorkerDTO.getId()); // Confirma que se creó correctamente
        assertEquals("Carlos Rodríguez", savedFarmWorkerDTO.getName()); // Verifica el usuario asignado
        assertEquals("Harvest Manager", savedFarmWorkerDTO.getJobType()); // Verifica el rol del trabajador
        assertEquals("Sunny Fields", savedFarmWorkerDTO.getFarm().getName()); // Confirma la granja asignada
    }

    @Test
    public void testFindFarmWorkerById() {
        FarmWorker worker = new FarmWorker("Irrigation Specialist", 1L);
        farmWorkerRepository.save(worker);

        Optional<FarmWorker> foundWorker = farmWorkerRepository.findById(worker.getId());

        assertTrue(foundWorker.isPresent()); // Verifica que el trabajador fue encontrado
        assertEquals("Maria López", foundWorker.get().getName()); // Compara el nombre
    }
}