package com.ecommerce.exception;

public class AuthorizationException extends Exception {

    private ExceptionMessages exceptionMessage;

    public AuthorizationException(ExceptionMessages exceptionMessage){
        super(exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessages getExceptionMessage(){
        return this.exceptionMessage;
    }
}
