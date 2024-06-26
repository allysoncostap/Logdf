package org.allysoncp.repositories.exceptions;

import jakarta.servlet.ServletRequest;
import org.allysoncp.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFundException(ObjectNotFoundException e, ServletRequest request){
        StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }
}
