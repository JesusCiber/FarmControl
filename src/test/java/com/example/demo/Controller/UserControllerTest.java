package com.example.demo.Controller;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User(null, "Alice Johnson", "555-1234", "alice@example.com", "securePass123");
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId()); // Verifica que el ID fue generado
        assertEquals("Alice Johnson", savedUser.getName()); // Verifica el nombre
        assertEquals("alice@example.com", savedUser.getEmail()); // Verifica el email
    }

    @Test
    public void testFindUserByEmail() {
        User user = new User(null, "Bob Smith", "555-5678", "bob@example.com", "password456");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("bob@example.com");

        assertTrue(foundUser.isPresent()); // Verifica que el usuario fue encontrado
        assertEquals("Bob Smith", foundUser.get().getName()); // Compara el nombre
    }
}
