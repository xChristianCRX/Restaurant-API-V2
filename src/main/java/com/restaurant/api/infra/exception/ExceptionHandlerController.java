package com.restaurant.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exception404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorDataDTO::new).toList());
    }

    private record ValidationErrorDataDTO(String field, String message){
        public ValidationErrorDataDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
