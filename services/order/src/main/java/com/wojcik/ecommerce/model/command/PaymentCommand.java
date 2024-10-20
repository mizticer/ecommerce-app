package com.wojcik.ecommerce.model.command;

import com.wojcik.ecommerce.model.PaymentMethod;
import com.wojcik.ecommerce.model.dto.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCommand {
    private Long id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Long orderId;
    private String orderReference;
    private Customer customer;
}

