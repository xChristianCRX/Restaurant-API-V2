package com.restaurant.api.domain.menu;

import com.restaurant.api.domain.menu.dto.CreateMenuItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "menu")
@Entity(name = "Menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String name;

    private String description;

    private Double price;

    @Enumerated(EnumType.STRING)
    private TypeItemENUM type;

    public MenuItemEntity(CreateMenuItemDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.type = data.type();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String descricao) {
        this.description = descricao;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setType(TypeItemENUM type) {
        this.type = type;
    }
}
