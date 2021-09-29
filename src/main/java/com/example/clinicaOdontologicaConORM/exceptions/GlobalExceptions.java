package com.example.clinicaOdontologicaConORM.exceptions;

import com.example.clinicaOdontologicaConORM.controllers.impl.OdontologoController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(OdontologoController.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarNotFound(ResourceNotFoundException ex) {
        logger.debug(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarBadRequest(BadRequestException ex) {
        logger.debug(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
