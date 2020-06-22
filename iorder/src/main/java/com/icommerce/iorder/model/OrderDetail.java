package com.icommerce.iorder.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderDetail {
    @NotNull
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Double price;

    @Min(value = 1)
    private int quantity;
}
