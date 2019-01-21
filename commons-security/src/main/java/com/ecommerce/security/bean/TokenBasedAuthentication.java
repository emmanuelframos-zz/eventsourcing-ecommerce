package com.ecommerce.security.bean;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    private String token;

    public TokenBasedAuthentication(String token){
        super(null);
        this.token = token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}