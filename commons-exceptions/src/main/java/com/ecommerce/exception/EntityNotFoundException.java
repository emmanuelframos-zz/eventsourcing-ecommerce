package com.ecommerce.exception;

public class EntityNotFoundException extends Exception {

    private ExceptionMessages exceptionMessage;

    public EntityNotFoundException(ExceptionMessages exceptionMessage){
        super(exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessages getExceptionMessage(){
        return this.exceptionMessage;
    }
}
