package com.wojcik.ecommerce.model.mapper;

import com.wojcik.ecommerce.model.Category;
import com.wojcik.ecommerce.model.Product;
import com.wojcik.ecommerce.model.command.ProductCommand;
import com.wojcik.ecommerce.model.dto.ProductDto;
import com.wojcik.ecommerce.model.dto.ProductPurchaseDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductCommand command) {
        return Product.builder()
                .name(command.getName())
                .availableQuantity(command.getAvailableQuantity())
                .price(command.getPrice())
                .availableQuantity(command.getAvailableQuantity())
                .description(command.getDescription())
                .category(Category.builder()
                        .id(command.getCategoryId())
                        .build())
                .build();
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }

    public ProductPurchaseDto toProductPurchaseResponse(Product product, double quantity) {
        return ProductPurchaseDto.builder()
                .Id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }
}
