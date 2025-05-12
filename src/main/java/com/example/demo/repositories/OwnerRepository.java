package com.example.demo.repositories;

import com.example.demo.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
