package com.ecommerce.ecom.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String,String>errorMessage=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMessage.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
