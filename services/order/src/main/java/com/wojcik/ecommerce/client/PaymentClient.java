package com.wojcik.ecommerce.client;

import com.wojcik.ecommerce.model.command.PaymentCommand;
import com.wojcik.ecommerce.model.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-serivce",
        url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping
    PaymentDto requestOrderPayment(@RequestBody PaymentCommand command);
}
