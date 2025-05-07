package models;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@EqualsAndHashCode(callSuper = true) // Considera los atributos de la clase heredada
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends User{
    private String type; // Individual, Company, etc.
}
