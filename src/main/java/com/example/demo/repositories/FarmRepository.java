package com.example.demo.repositories;


import com.example.demo.models.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm, Long> {
    List<Farm> findByOwnerId(Long ownerId);
    List<Farm> findByLocationContaining(String location);

}
