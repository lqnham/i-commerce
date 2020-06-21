package com.icommerce.iproduct.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String branchName;
    private String color;
    private int quantity;
    private boolean delete;

    public Product() {
    }

    public Product(final Long id, final String name, final String description, final String branchName, final String color, Double price, int quantity, boolean delete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.branchName = branchName;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.delete = delete;
    }

}
