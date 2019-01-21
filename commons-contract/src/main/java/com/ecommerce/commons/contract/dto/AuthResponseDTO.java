package com.ecommerce.commons.contract.dto;

public class AuthResponseDTO {

    public String authToken;

    public AuthResponseDTO(){}

    public AuthResponseDTO(String authToken) {
        this.authToken = authToken;
    }

}