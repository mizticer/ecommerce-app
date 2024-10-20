package com.wojcik.ecommerce.model.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateCommand {
    @NotNull(message ="Customer firstname is required")
    private String firstName;

    @NotNull(message ="Customer lastname is required")
    private String lastName;

    @NotNull(message ="Customer email is required")
    @Email(message = "Customer email is not a valid email address")
    private String email;
    private AddressCommand address;
}
