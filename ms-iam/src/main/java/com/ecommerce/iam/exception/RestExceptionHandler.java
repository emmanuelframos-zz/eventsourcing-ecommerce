package com.ecommerce.iam.exception;

import com.ecommerce.commons.exceptions.dto.ErrorDTO;
import com.ecommerce.commons.exceptions.exception.AuthenticationException;
import com.ecommerce.commons.exceptions.exception.AuthorizationException;
import com.ecommerce.commons.exceptions.exception.ExceptionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity handleAuthenticationException(AuthenticationException ex) {
        logger.error("AuthenticationException", ex);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AuthorizationException.class})
    public ResponseEntity handleAuthorizationException(AuthorizationException ex) {
        logger.error("AuthorizationException", ex);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleGenericException(Exception ex) {
        logger.error("Exception", ex);
        return new ResponseEntity<>(ErrorDTO.build().message(ExceptionMessages.GENERIC.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}