package com.restaurant.api.domain.table;

import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tables")
@Entity(name = "Table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableEntity {

    @Id
    private int tableNumber;

    @OneToMany
    @JoinTable(
            name = "table_history",
            joinColumns = @JoinColumn(name = "tableNumber"),
            inverseJoinColumns = @JoinColumn(name = "orderHistory_id")
    )
    private List<OrderHistoryEntity> orderHistory;

}
