package com.restaurant.api.domain.person;

import lombok.Getter;

@Getter
public enum UserRoleENUM {
    ADMIN("admin"),
    CASHIER("cashier"),
    WAITER("waiter");

    private final String role;

    UserRoleENUM(String role){
        this.role = role;
    }
}
