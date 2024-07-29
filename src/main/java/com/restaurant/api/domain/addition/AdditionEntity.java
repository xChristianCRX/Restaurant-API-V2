package com.restaurant.api.domain.addition;

import com.restaurant.api.domain.addition.dto.CreateAdditionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "additions")
@Entity(name = "Addition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Double price;

    public AdditionEntity(CreateAdditionDTO data){
        this.name = data.name();
        this.price = data.price();
    }
}
