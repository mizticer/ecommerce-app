package com.wojcik.ecommerce.client;

import com.wojcik.ecommerce.model.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/{id}")
    Optional<Customer> findCustomerById(@PathVariable("id") String id);

}
