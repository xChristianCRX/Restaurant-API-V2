package com.restaurant.api.domain.table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tables")
@Entity(name = "Table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableEntity {
    @Id
    private Integer tableNumber;
}
