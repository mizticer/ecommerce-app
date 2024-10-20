package com.wojcik.ecommerce.model.mapper;

import com.wojcik.ecommerce.model.Payment;
import com.wojcik.ecommerce.model.command.PaymentCommand;
import com.wojcik.ecommerce.model.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentCommand command) {
        return Payment.builder()
                .id(command.getId())
                .paymentMethod(command.getPaymentMethod())
                .amount(command.getAmount())
                .orderId(command.getOrderId())
                .build();
    }

    public PaymentDto toDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .build();
    }
}
