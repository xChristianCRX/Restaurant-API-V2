package com.restaurant.api.domain.tableOrder;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.person.UserEntity;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Table(name = "tables_order")
@Entity(name = "TableOrder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = true)
    private MenuItemEntity item;

    @ManyToMany
    @JoinTable(
            name = "rl_item_additions",
            joinColumns = @JoinColumn(name = "fk_tableOrder"),
            inverseJoinColumns = @JoinColumn(name = "fk_addition")
    )
    private List<AdditionEntity> additions;

    @ManyToOne
    private UserEntity waiter;

    private String observations;

    public TableOrderEntity(CreateTableOrderDTO data){
        this.item = data.item();
        this.waiter = data.waiter();
        this.additions = data.additions();
        this.observations = data.observations();
    }

    public TableOrderEntity(MenuItemEntity item, UserEntity waiter, List<AdditionEntity> additions, String observations){
        this.item = item;
        this.waiter = waiter;
        this.additions = additions;
        this.observations = observations;
    }
}
