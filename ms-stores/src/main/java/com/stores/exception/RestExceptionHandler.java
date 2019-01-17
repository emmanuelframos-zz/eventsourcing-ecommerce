package com.stores.exception;

import com.ecommerce.exception.EntityNotFoundException;
import com.ecommerce.exception.ExceptionMessages;
import com.stores.api.dto.ErrorDTO;
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

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.error("EntityNotFoundException", ex);
        return new ResponseEntity<>(ErrorDTO.build().message(ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleGenericException(Exception ex) {
        logger.error("Exception", ex);
        return new ResponseEntity<>(ErrorDTO.build().message(ExceptionMessages.GENERIC.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}