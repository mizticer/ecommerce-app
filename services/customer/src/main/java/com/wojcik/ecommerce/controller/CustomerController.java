package com.wojcik.ecommerce.controller;

import com.wojcik.ecommerce.model.command.CustomerCommand;
import com.wojcik.ecommerce.model.command.CustomerUpdateCommand;
import com.wojcik.ecommerce.model.dto.CustomerDto;
import com.wojcik.ecommerce.model.dto.ExistsResponseDto;
import com.wojcik.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody @Valid CustomerCommand command) {
        return customerService.create(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerUpdateCommand command) {
        return customerService.update(id, command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/exists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExistsResponseDto existsCustomerById(@PathVariable String id) {
        return customerService.existsById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto findById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteById(id);
    }
}
