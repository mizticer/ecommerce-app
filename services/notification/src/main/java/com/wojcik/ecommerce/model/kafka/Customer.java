package com.wojcik.ecommerce.model.kafka;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {

}
