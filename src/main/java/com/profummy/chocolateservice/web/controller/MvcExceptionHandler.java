package com.profummy.chocolateservice.web.controller;

import com.profummy.chocolateservice.web.errorresponses.ValidationErrorResponse;
import com.profummy.chocolateservice.web.errorresponses.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleValidationExceptions(ConstraintViolationException e) {

        ValidationErrorResponse errors = new ValidationErrorResponse();

        e.getConstraintViolations().forEach(violation -> {
            errors.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        });

        return errors;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleMethodArgumentExceptions(MethodArgumentNotValidException e) {

        ValidationErrorResponse errors = new ValidationErrorResponse();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        });

        return errors;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleBindExceptions(BindException e) {

        ValidationErrorResponse errors = new ValidationErrorResponse();

        e.getAllErrors().forEach(error -> {
            errors.getViolations().add(new Violation(error.getObjectName(), error.getDefaultMessage()));
        });

        return errors;
    }
}
