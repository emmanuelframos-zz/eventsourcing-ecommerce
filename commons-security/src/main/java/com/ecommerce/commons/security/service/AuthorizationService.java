package com.ecommerce.commons.security.service;

import com.ecommerce.commons.contract.dto.AuthResponseDTO;
import com.ecommerce.commons.exceptions.exception.AuthorizationException;
import com.ecommerce.commons.exceptions.exception.ExceptionMessages;
import com.ecommerce.commons.restclient.UnderratedRestClient;
import com.ecommerce.commons.security.bean.TokenBasedAuthentication;
import com.ecommerce.commons.security.loader.EntriesLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpMethod.OPTIONS;

@Service
public class AuthorizationService {

    private final EntriesLoader entriesLoader;

    public AuthorizationService(EntriesLoader entriesLoader) {
        this.entriesLoader = entriesLoader;
    }

    public Authentication authorize(HttpServletRequest httpServletRequest) throws AuthorizationException {

        if (httpServletRequest.getMethod().equals(OPTIONS.name()))
            return null;

        if (!httpServletRequest.getRequestURI().contains("/api/v1"))
            return null;

        String jwtToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isEmpty(jwtToken))
            throw new AuthorizationException(ExceptionMessages.AUTHORIZATION_ERROR);

        ResponseEntity<AuthResponseDTO> response = UnderratedRestClient
                .build()
                .baseUrl(entriesLoader.getAuthorizeUrl())
                .method(HttpMethod.POST)
                .payload(new AuthResponseDTO(jwtToken))
                .execute(AuthResponseDTO.class);

        if (!response.getStatusCode().is2xxSuccessful())
            throw new AuthorizationException(ExceptionMessages.AUTHORIZATION_ERROR);

        return new TokenBasedAuthentication(jwtToken);
    }
}