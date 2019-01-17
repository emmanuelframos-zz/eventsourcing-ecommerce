package com.ecommerce.exception;

public enum ExceptionMessages {

    GENERIC("An error ocurred"),
    STORE_NOT_FOUND("Store no found");

    private String message;

    ExceptionMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}