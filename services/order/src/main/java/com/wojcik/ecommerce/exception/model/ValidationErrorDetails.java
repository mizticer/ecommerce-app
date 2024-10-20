package com.wojcik.ecommerce.exception.model;

import lombok.Value;

@Value
public class ValidationErrorDetails {
    private String field;
    private String message;
}
