package com.ecommerce.exception;

public class AuthenticationException extends Exception {

    private ExceptionMessages exceptionMessage;

    public AuthenticationException(ExceptionMessages exceptionMessage){
        super(exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessages getExceptionMessage(){
        return this.exceptionMessage;
    }
}
