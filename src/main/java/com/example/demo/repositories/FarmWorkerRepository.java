package com.example.demo.repositories;

import com.example.demo.models.FarmWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmWorkerRepository extends JpaRepository<FarmWorker, Long> {
    List<FarmWorker> findByFarmId(Long farmId);
}
