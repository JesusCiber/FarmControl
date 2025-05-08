package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Owner extends User{
    private String type; // Individual, Company, etc.
}
