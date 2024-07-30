package com.restaurant.api.domain.tableOrder;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.person.PersonEntity;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
            name = "item_additions",
            joinColumns = @JoinColumn(name = "tableOrder_id"),
            inverseJoinColumns = @JoinColumn(name = "addition_id")
    )
    private List<AdditionEntity> additions;

    private String observations;

    @Timestamp
    private LocalDateTime createdAt;
}
