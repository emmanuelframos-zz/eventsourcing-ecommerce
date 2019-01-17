package com.stores.api.dto;

import java.io.Serializable;

public final class ErrorDTO implements Serializable {

    public String message;

    private ErrorDTO(){}

    public static ErrorDTO build(){
        return new ErrorDTO();
    }

    public ErrorDTO message(String message){
        this.message = message;
        return this;
    }
}