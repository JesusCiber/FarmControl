package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true) // Considera los atributos de la clase heredada
@AllArgsConstructor
@NoArgsConstructor
public class FarmWorker extends User{

    private String jobType;

    @ManyToOne
    @JoinColumn(name="farm_id")
    private Farm farm;


}
