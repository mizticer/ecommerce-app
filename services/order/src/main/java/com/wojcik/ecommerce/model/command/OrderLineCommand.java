package com.wojcik.ecommerce.model.command;

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
public class OrderLineCommand {
    private Long id;
    private Long orderId;
    private Long productId;
    private double quantity;
}
