package com.restaurant.api.domain.orderHistory;

import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Table(name = "order_history")
@Entity(name = "OrderHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "table_number", nullable = false)
    private TableEntity tableNumber;

    @OneToMany
    @JoinTable(
            name = "rl_orderhistory_tableorders",
            joinColumns = @JoinColumn(name = "tableOrder_id"),
            inverseJoinColumns = @JoinColumn(name = "addition_id")
    )
    private List<TableOrderEntity> tableOrders;

    private Boolean active;
}
