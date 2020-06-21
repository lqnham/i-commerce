package com.icommerce.iorder.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class Order {
    private Long orderId;
    private Date date;
    private String customerId;
    private Double sumPrice;
    private Set<OrderDetail> detail;
}
