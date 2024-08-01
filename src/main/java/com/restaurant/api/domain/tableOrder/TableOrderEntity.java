package com.restaurant.api.domain.tableOrder;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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

    @ManyToOne
    private MenuItemEntity item;

    @ManyToOne
    private PersonEntity waiter;

    @ManyToMany
    @JoinTable(
            name = "rl_item_additions",
            joinColumns = @JoinColumn(name = "tableOrder_id"),
            inverseJoinColumns = @JoinColumn(name = "addition_id")
    )
    private List<AdditionEntity> additions;

    private String observations;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public TableOrderEntity(CreateTableOrderDTO data){
        this.item = data.item();
        this.waiter = data.waiter();
        this.additions = data.additions();
        this.observations = data.observations();
    }

    public TableOrderEntity(MenuItemEntity item, PersonEntity waiter, List<AdditionEntity> additions, String observations){
        this.item = item;
        this.waiter = waiter;
        this.additions = additions;
        this.observations = observations;
    }
}
