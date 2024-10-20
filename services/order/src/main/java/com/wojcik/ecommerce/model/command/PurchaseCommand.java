package com.wojcik.ecommerce.model.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class PurchaseCommand {

    @NotNull(message = "Product is mandatory")
    private Long productId;

    @Positive(message = "Quantity is mandatory")
    private double quantity;
}
