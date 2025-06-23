package com.restaurant.api.domain.table;

import lombok.Getter;

@Getter
public enum TableStatusENUM {
    AVAILABLE("available"),
    OCCUPIED("occupied"),
    CLOSED("closed"),
    RESERVED("reserved");

    private final String status;

    TableStatusENUM(String status){
        this.status = status;
    }
}
