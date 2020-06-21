package com.icommerce.iproduct.handler;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Could not find product with ID: " + id);
    }
}
