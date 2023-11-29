package com.bouali.ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public void purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    productUrl + "/purchase",
                    POST,
                    requestEntity,
                    Void.class
            );

            if (responseEntity.getStatusCode().isError()) {
                throw new RuntimeException("Non-2xx response received: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while making the POST request", e);
        }
    }

}
