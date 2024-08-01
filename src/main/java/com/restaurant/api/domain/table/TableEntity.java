package com.restaurant.api.domain.table;

import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import com.restaurant.api.domain.table.dto.CreateTableDTO;
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
    private Integer tableNumber;

    @OneToMany
    @JoinTable(
            name = "rl_table_orderhistory",
            joinColumns = @JoinColumn(name = "tableNumber"),
            inverseJoinColumns = @JoinColumn(name = "orderHistory_id")
    )
    private List<OrderHistoryEntity> orderHistory;

    public TableEntity(CreateTableDTO data) {
        this.tableNumber = data.tableNumber();
    }

}
