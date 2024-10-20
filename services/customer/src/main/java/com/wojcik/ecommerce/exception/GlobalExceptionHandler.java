package com.wojcik.ecommerce.exception;

import com.wojcik.ecommerce.exception.model.ExceptionDto;
import com.wojcik.ecommerce.exception.model.ValidationErrorDetails;
import com.wojcik.ecommerce.exception.model.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException e) {
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDto handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ValidationErrorDetails> errorDetails = ex.getFieldErrors().stream()
                .map(fe -> new ValidationErrorDetails(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ValidationErrorDto(errorDetails);
    }
}