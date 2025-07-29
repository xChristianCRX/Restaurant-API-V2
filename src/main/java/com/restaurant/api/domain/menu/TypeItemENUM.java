package com.restaurant.api.domain.menu;

import lombok.Getter;

@Getter
public enum TypeItemENUM {
    BURGUER("burguer"),
    DRINK("drink"),
    APPETIZER("appetizer");

    private final String typeItem;

    TypeItemENUM(String typeItem){
        this.typeItem = typeItem;
    }
}
