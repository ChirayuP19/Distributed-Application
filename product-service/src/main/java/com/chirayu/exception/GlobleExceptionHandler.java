package com.chirayu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return new ResponseEntity<>(resourceNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateProductFound.class)
    public ResponseEntity<String> handleDuplicateResourceFoundException(DuplicateProductFound duplicateProductFound){
        return new ResponseEntity<>(duplicateProductFound.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
