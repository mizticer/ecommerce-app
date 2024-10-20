package com.wojcik.ecommerce.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ProductProperties {

    @Value("${application.config.product-url}")
    private String productUrl;
}
