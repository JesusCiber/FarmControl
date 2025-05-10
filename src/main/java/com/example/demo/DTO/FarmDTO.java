package com.example.demo.DTO;

import com.example.demo.models.Farm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmDTO {
    private Long id;
    private String name;
    private String location;

    public FarmDTO(Farm farm) {
        this.id = farm.getId();
        this.name = farm.getName();
        this.location = farm.getLocation();
    }
}
