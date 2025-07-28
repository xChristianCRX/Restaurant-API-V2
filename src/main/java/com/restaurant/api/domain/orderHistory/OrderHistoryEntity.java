package com.restaurant.api.domain.orderHistory;

import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private TableEntity table;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rl_orderhistory_tableorders",
            joinColumns = @JoinColumn(name = "history_id"),
            inverseJoinColumns = @JoinColumn(name = "table_order_id")
    )
    private List<TableOrderEntity> tableOrders;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private Boolean active;

    public OrderHistoryEntity(TableEntity tableEntity, List<TableOrderEntity> tableOrders, Boolean active){
        this.table = tableEntity;
        this.tableOrders = tableOrders;
        this.active = active;
        this.createdAt = LocalDateTime.now();
    }
}
