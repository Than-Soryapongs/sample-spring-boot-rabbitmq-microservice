package com.pongs.order_service.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

// Remove later

@Component
public class InventoryServiceClient {

    private final RestTemplate restTemplate;
    private final String inventoryServiceUrl;

    public InventoryServiceClient(RestTemplate restTemplate,
                                   @Value("${inventory-service.url}") String inventoryServiceUrl) {
        this.restTemplate = restTemplate;
        this.inventoryServiceUrl = inventoryServiceUrl;
    }

    public boolean deductStock(String productId, int quantity) {
        Map<String, Object> body = new HashMap<>();
        body.put("productId", productId);
        body.put("quantity", quantity);

        try {
            restTemplate.exchange(
                    inventoryServiceUrl + "/inventory",
                    HttpMethod.PUT,
                    new HttpEntity<>(body),
                    Object.class
            );
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }
}
