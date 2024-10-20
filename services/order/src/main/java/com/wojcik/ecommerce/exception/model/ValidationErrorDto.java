package com.wojcik.ecommerce.exception.model;

import lombok.Value;

import java.util.List;

@Value
public class ValidationErrorDto {
    private List<ValidationErrorDetails> errorValidations;

    public ValidationErrorDto(List<ValidationErrorDetails> errorValidations) {
        this.errorValidations = errorValidations;
    }
}