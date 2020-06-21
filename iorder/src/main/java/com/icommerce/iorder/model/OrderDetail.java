package com.icommerce.iorder.model;

import lombok.Data;

@Data
public class OrderDetail {
    private Long id;
    private Long orderId;
    private Long productId;
}
