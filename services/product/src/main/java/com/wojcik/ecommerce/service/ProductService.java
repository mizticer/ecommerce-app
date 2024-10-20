package com.wojcik.ecommerce.service;

import com.wojcik.ecommerce.exception.NotFoundException;
import com.wojcik.ecommerce.exception.ProductPurchaseException;
import com.wojcik.ecommerce.model.Product;
import com.wojcik.ecommerce.model.command.ProductCommand;
import com.wojcik.ecommerce.model.command.ProductPurchaseCommand;
import com.wojcik.ecommerce.model.dto.ProductDto;
import com.wojcik.ecommerce.model.dto.ProductPurchaseDto;
import com.wojcik.ecommerce.model.mapper.ProductMapper;
import com.wojcik.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto create(ProductCommand productCommand) {
        Product product = productMapper.toProduct(productCommand);
        return productMapper.toDto(productRepository.save(product));
    }

    @Transactional
    public List<ProductPurchaseDto> purschaseProducts(List<ProductPurchaseCommand> productCommand) {
        List<Long> productIds = productCommand.stream()
                .map(ProductPurchaseCommand::getProductId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        List<ProductPurchaseCommand> storedCommand = productCommand.stream()
                .sorted(Comparator.comparing(ProductPurchaseCommand::getProductId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseDto>();

        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseCommand productPurchaseCommand = storedCommand.get(i);
            if (product.getAvailableQuantity() < productPurchaseCommand.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productPurchaseCommand.getProductId());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productPurchaseCommand.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productPurchaseCommand.getQuantity()));
        }
        return purchasedProducts;
    }

    @Transactional(readOnly = true)
    public ProductDto getById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new NotFoundException(MessageFormat
                        .format("Customer with id={0} not found", id)));
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
