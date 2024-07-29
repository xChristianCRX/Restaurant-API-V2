package com.restaurant.api.domain.menu;

public enum TypeItemENUM {
    BURGUER("burguer"),
    DRINK("drink"),
    PORTION("portion");

    private String typeItem;

    TypeItemENUM(String typeItem){
        this.typeItem = typeItem;
    }

    public String getTypeItem(){
        return typeItem;
    }
}
