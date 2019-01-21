package com.ecommerce.commons.exceptions.exception;

public enum ExceptionMessages {

    GENERIC("An error ocurred"),
    AUTHENTICATION_ERROR("Authentication error"),
    AUTHORIZATION_ERROR("Authorization error"),
    INVALID_CREDENTIALS("Invalid credentials"),
    STORE_NOT_FOUND("Store no found");

    private String message;

    ExceptionMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}