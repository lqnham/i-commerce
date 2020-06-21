package com.icommerce.iproduct.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Double price;

    @NotBlank
    private String branchName;

    private String color;

    private int quantity;

    private boolean delete = false;
}
