package com.iam.api.dto;

public class AuthResponseDTO {

    public String authToken;

    public AuthResponseDTO(){}

    public AuthResponseDTO(String authToken) {
        this.authToken = authToken;
    }

}