package com.example.demo.DTO;

import com.example.demo.models.FarmJob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmJobDTO {
    private Long id;
    private String description;
    private Long farmId;

    public FarmJobDTO(FarmJob farmJob) {
        this.id = farmJob.getId();
        this.description = farmJob.getDescription();
        this.farmId = (farmJob.getFarm() != null) ? farmJob.getFarm().getId() : null;
    }
}
