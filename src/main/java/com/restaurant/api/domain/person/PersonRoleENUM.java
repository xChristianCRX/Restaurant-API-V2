package com.restaurant.api.domain.person;

import lombok.Getter;

@Getter
public enum PersonRoleENUM {
    ADMIN("admin"),
    CASHIER("cashier"),
    WAITER("waiter");

    private final String role;

    PersonRoleENUM(String role){
        this.role = role;
    }
}
