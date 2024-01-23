package com.barbershop.barber.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Errors {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ErrorDataValidation::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity ErrorValidators(ValidationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record ErrorDataValidation(String field, String msg) {
        public ErrorDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());

        }
    }
}
