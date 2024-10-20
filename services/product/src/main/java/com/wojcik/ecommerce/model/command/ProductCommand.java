package com.wojcik.ecommerce.model.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ProductCommand {

    @NotNull(message ="Product name is required")
    private String name;

    @NotNull(message ="Product description is required")
    private String description;

    @Positive(message ="Product availableQuantity should be positive")
    private double availableQuantity;

    @Positive(message ="Product price should be positive")
    private BigDecimal price;

    @NotNull(message ="Product category is required")
    private Long categoryId;
}
