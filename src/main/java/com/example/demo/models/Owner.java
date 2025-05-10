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

    @OneToMany(mappedBy = "owner") // Relación inversa de Many-To-One
    private List<Farm> farm;
}
