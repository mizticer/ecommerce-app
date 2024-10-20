package com.wojcik.ecommerce.model.mapper;

import com.wojcik.ecommerce.model.Order;
import com.wojcik.ecommerce.model.OrderLine;
import com.wojcik.ecommerce.model.command.OrderLineCommand;
import com.wojcik.ecommerce.model.dto.OrderLineDto;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toEntity(OrderLineCommand orderLineCommand) {
        return OrderLine.builder()
                .id(orderLineCommand.getId())
                .quantity(orderLineCommand.getQuantity())
                .productId(orderLineCommand.getProductId())
                .order(Order.builder()
                        .id(orderLineCommand.getOrderId())
                        .build())
                .build();
    }

    public OrderLineDto toOrderLineDto(OrderLine orderLine) {
        return OrderLineDto.builder()
                .id(orderLine.getId())
                .quntity(orderLine.getQuantity())
                .build();
    }
}
