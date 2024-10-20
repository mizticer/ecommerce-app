package com.wojcik.ecommerce.model.mapper;

import com.wojcik.ecommerce.model.Address;
import com.wojcik.ecommerce.model.Customer;
import com.wojcik.ecommerce.model.command.CustomerCommand;
import com.wojcik.ecommerce.model.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerCommand command) {
        return Customer.builder()
                .id(command.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .address(Address.builder()
                        .street(command.getAddress().getStreet())
                        .houseNumber(command.getAddress().getHouseNumber())
                        .zipCode(command.getAddress().getZipCode())
                        .build())
                .build();
    }

    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }
}
