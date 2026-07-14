package com.pongs.inventory_service.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String productId) {
        super("Product already existed: " + productId);
    }
}