package com.restaurant.api.infra.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(){
        super("User already exists!");
    }
}
