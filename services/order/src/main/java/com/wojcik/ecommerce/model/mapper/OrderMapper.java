package com.wojcik.ecommerce.model.mapper;

import com.wojcik.ecommerce.model.Order;
import com.wojcik.ecommerce.model.command.OrderCommand;
import com.wojcik.ecommerce.model.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderCommand command) {
        return Order.builder()
                .id(command.getId())
                .customerId(command.getCustomerId())
                .reference(command.getReference())
                .totalAmount(command.getAmount())
                .paymentMethod(command.getPaymentMethod())
                .build();
    }

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .paymentMethod(order.getPaymentMethod())
                .reference(order.getReference())
                .customerId(order.getCustomerId())
                .totalAmount(order.getTotalAmount())
                .build();
    }
}
