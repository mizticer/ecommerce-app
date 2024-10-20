package com.wojcik.ecommerce.service;

import com.wojcik.ecommerce.exception.NotFoundException;
import com.wojcik.ecommerce.model.Address;
import com.wojcik.ecommerce.model.Customer;
import com.wojcik.ecommerce.model.command.CustomerCommand;
import com.wojcik.ecommerce.model.command.CustomerUpdateCommand;
import com.wojcik.ecommerce.model.dto.CustomerDto;
import com.wojcik.ecommerce.model.dto.ExistsResponseDto;
import com.wojcik.ecommerce.model.mapper.CustomerMapper;
import com.wojcik.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerDto create(CustomerCommand customerCommand) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerCommand));
        return customerMapper.toDto(customer);
    }

    @Transactional
    public CustomerDto update(String id, CustomerUpdateCommand command) {
        Customer updatedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat
                        .format("Customer with id={0} not found", id)));
        updatedCustomer.setFirstName(command.getFirstName());
        updatedCustomer.setLastName(command.getLastName());
        updatedCustomer.setEmail(command.getEmail());
        updatedCustomer.setAddress(Address.builder()
                .street(command.getAddress().getStreet())
                .houseNumber(command.getAddress().getHouseNumber())
                .zipCode(command.getAddress().getZipCode())
                .build());
        return customerMapper.toDto(customerRepository.save(updatedCustomer));
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ExistsResponseDto existsById(String id) {
        boolean exists = customerRepository.findById(id).isPresent();
        return new ExistsResponseDto(exists);
    }

    @Transactional(readOnly = true)
    public CustomerDto findById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new NotFoundException(MessageFormat
                        .format("Customer with id={0} not found", id)));
    }

    @Transactional
    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }
}
