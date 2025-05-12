package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Owner extends User{
    private String type; // Individual, Company, etc.

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL) // Relación inversa de Many-To-One
    private List<Farm> farm;

    public Owner(Long id, String name, String phoneNumber, String email, String password, String type, List<Farm> farm) {
        super(id, name, phoneNumber, email, password);
        this.type = type;
        this.farm = farm;
    }

}
