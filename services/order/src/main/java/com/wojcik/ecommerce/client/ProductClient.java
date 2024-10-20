package com.wojcik.ecommerce.client;

import com.wojcik.ecommerce.exception.BusinessException;
import com.wojcik.ecommerce.model.command.PurchaseCommand;
import com.wojcik.ecommerce.model.dto.Product;
import com.wojcik.ecommerce.properties.ProductProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final ProductProperties productProperties;
    private final RestTemplate restTemplate;

    public List<Product> purchaseProducts(List<PurchaseCommand> commands) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.APPLICATION_JSON));

        HttpEntity<List<PurchaseCommand>> requestEntity = new HttpEntity<>(commands, headers);
        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                productProperties.getProductUrl() + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }

}

