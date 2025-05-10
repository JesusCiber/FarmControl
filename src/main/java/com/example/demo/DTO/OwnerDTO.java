package com.example.demo.DTO;

import com.example.demo.models.Farm;
import com.example.demo.models.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OwnerDTO {
    private Long userId;
    private String name;
    private String phoneNumber;
    private String email;
    private List<FarmDTO> farms;

    public OwnerDTO(Owner owner) {
        this.userId = owner.getId();
        this.name =  owner.getName();
        this.phoneNumber = owner.getPhoneNumber();
        this.email = owner.getEmail();
        this.farms = owner.getFarm().stream()
                .map(farm -> new FarmDTO(farm))
                .collect(Collectors.toList());
    }

}
