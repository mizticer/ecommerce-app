package com.wojcik.ecommerce.model.command;

import jakarta.validation.constraints.NotNull;
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
public class ProductPurchaseCommand {

    @NotNull(message ="Product id is required")
    private Long productId;

    @NotNull(message ="Product quantity is required")
    private double quantity;
}
