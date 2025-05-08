package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true) // Considera los atributos de la clase heredada
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class FarmWorker extends User{

    private String jobType;

    @ManyToOne
    @JoinColumn(name="farm_id")
    private Farm farm;


}
