package com.example.demo.DTO;

import com.example.demo.models.FarmWorker;
import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmWorkerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String jobType;
    private Long farmId;

    public FarmWorkerDTO(FarmWorker worker) {
        this.id = worker.getId();
        this.name = worker.getName();
        this.phoneNumber = worker.getPhoneNumber();
        this.email = worker.getEmail();
        this.jobType = worker.getJobType();
        this.farmId = (worker.getFarm() != null) ? worker.getFarm().getId() : null;
    }
}