package com.wojcik.ecommerce.controller;

import com.wojcik.ecommerce.model.command.ProductCommand;
import com.wojcik.ecommerce.model.command.ProductPurchaseCommand;
import com.wojcik.ecommerce.model.dto.ProductDto;
import com.wojcik.ecommerce.model.dto.ProductPurchaseDto;
import com.wojcik.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody @Valid ProductCommand productCommand) {
        return productService.create(productCommand);
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductPurchaseDto> purchaseProduct(@RequestBody @Valid List<ProductPurchaseCommand> productPurchaseCommandList) {
        return productService.purschaseProducts(productPurchaseCommandList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<ProductDto> getProducts(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return productService.findAll(pageable);
    }
}
