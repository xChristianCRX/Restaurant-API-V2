package com.restaurant.api.domain.person;

public enum PersonRoleENUM {
    ADMIN("admin"),
    CASHIER("cashier"),
    WAITER("waiter");

    private String role;

    PersonRoleENUM(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
